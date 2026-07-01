package com.example.gahramheit.service;

import com.example.gahramheit.dto.*;
import com.example.gahramheit.entity.*;
import com.example.gahramheit.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.transaction.support.TransactionTemplate;
import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
@SuppressWarnings({"SpellCheckingInspection", "unused"})
public class DataPopulatorService {
    private final AnimeRepository animeRepository;
    private final GenreRepository genreRepository;
    private final EpisodeRepository episodeRepository;
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final PlatformTransactionManager transactionManager;

    private final RestTemplate restTemplate = new RestTemplate();
    private static final String JIKAN_BASE_URL = "https://api.jikan.moe/v4";

    public void syncTopAnime(int pagesToSync) {
        log.info("🚀 INICIANDO BUCLE AUTOMÁTICO PARA {} PÁGINAS...", pagesToSync);

        // usuarios en la BD para las Reviews
        List<User> users = seedUsers();

        // recorre las páginas automáticamente
        for (int page = 1; page <= pagesToSync; page++) {
            log.info("===========================================");
            log.info("📚 DESCARGANDO PÁGINA {} DE {}", page, pagesToSync);
            log.info("===========================================");

            try {
                String url = JIKAN_BASE_URL + "/top/anime?page=" + page;
                JikanTopAnimeResponse response = restTemplate.getForObject(url, JikanTopAnimeResponse.class);
                sleepToAvoidRateLimit();

                if (response != null && response.getData() != null) {
                    for (JikanTopAnimeResponse.AnimeData animeData : response.getData()) {
                        // Aquí ocurre la magia: guarda las 4 entidades relacionadas
                        processSingleAnime(animeData, users);
                    }
                    log.info("✅ PÁGINA {} COMPLETADA.", page);
                }
            } catch (Exception e) {
                log.error("❌ Error en la página {}: {}", page, e.getMessage());
            }
        }
        log.info("🎉 ¡SINCRONIZACIÓN FINALIZADA!");
    }

    private void processSingleAnime(JikanTopAnimeResponse.AnimeData data, List<User> users) {
        // Si ya existe sale antes de abrir una transacción
        if (animeRepository.existsById(data.getMal_id())) {
            return;
        }
        if (animeRepository.count() >= 1000) {
            log.info("🎯 ¡Alcanzamos los 1,000 animes exactos! Ignorando el resto de la página.");
            return;
        }

        // cada anime abre y cierra su transaccion
        new TransactionTemplate(transactionManager).executeWithoutResult(status -> {
            log.info("📥 Procesando en transacción: {}", data.getTitle());

            // ANIME
            Anime anime = new Anime();
            anime.setId(data.getMal_id());
            anime.setTitle(data.getTitle());
            anime.setEpisodesCount(data.getEpisodes() != null ? data.getEpisodes() : 12);
            anime.setStatus(data.getStatus() != null ? data.getStatus() : "Finished Airing");
            anime.setReleaseYear(data.getYear());

            String synopsis = data.getSynopsis();
            if (synopsis != null && synopsis.length() > 2000) synopsis = synopsis.substring(0, 2000);
            anime.setSynopsis(synopsis);

            if (data.getImages() != null && data.getImages().getJpg() != null) {
                anime.setImageUrl(data.getImages().getJpg().getImage_url());
            }
            if (data.getStudios() != null && !data.getStudios().isEmpty()) {
                anime.setStudio(data.getStudios().getFirst().getName());
            }

            // GENRE
            Set<Genre> animeGenres = new HashSet<>();
            if (data.getGenres() != null) {
                for (JikanTopAnimeResponse.GenreDto gDto : data.getGenres()) {
                    Genre genre = genreRepository.findByName(gDto.getName())
                            .orElseGet(() -> {
                                Genre newGenre = new Genre();

                                newGenre.setMalId(gDto.getMal_id());
                                newGenre.setName(gDto.getName());
                                return genreRepository.save(newGenre);
                            });
                    animeGenres.add(genre);
                }
            }
            anime.setGenres(animeGenres);

            // Anime base
            animeRepository.save(anime);

            // trae directores y actores
            fetchStaffAndCast(anime);

            // EPISODE
            fetchEpisodes(anime);

            // REVIEW
            fetchReviews(anime, users);
        });
    }

    private void fetchStaffAndCast(Anime anime) {
        try {
            String staffUrl = JIKAN_BASE_URL + "/anime/" + anime.getId() + "/staff";
            JikanStaffResponse staffResp = restTemplate.getForObject(staffUrl, JikanStaffResponse.class);
            if (staffResp != null && staffResp.getData() != null) {
                String director = staffResp.getData().stream()
                        .filter(s -> s.getPositions().stream().anyMatch(pos -> pos.toLowerCase().contains("director")))
                        .map(s -> s.getPerson().getName())
                        .limit(2).reduce((a, b) -> a + ", " + b).orElse("Desconocido");
                anime.setDirector(director);
            }
            sleepToAvoidRateLimit();

            String charUrl = JIKAN_BASE_URL + "/anime/" + anime.getId() + "/characters";
            JikanCharacterResponse charResp = restTemplate.getForObject(charUrl, JikanCharacterResponse.class);
            if (charResp != null && charResp.getData() != null) {
                String cast = charResp.getData().stream()
                        .flatMap(c -> c.getVoice_actors().stream())
                        .filter(va -> "Japanese".equalsIgnoreCase(va.getLanguage()))
                        .map(va -> va.getPerson().getName())
                        .distinct().limit(5)
                        .reduce((a, b) -> a + ", " + b).orElse("Desconocidos");
                anime.setVoiceActors(cast);
            }
            sleepToAvoidRateLimit();
            animeRepository.save(anime); // anime con voces y directores
        } catch (Exception e) {}
    }

    private void fetchEpisodes(Anime anime) {
        try {
            String epUrl = JIKAN_BASE_URL + "/anime/" + anime.getId() + "/episodes";
            JikanEpisodeResponse epResp = restTemplate.getForObject(epUrl, JikanEpisodeResponse.class);
            if (epResp != null && epResp.getData() != null) {
                for (JikanEpisodeResponse.EpisodeData epData : epResp.getData()) {
                    Episode episode = new Episode();
                    episode.setAnime(anime);
                    episode.setEpisodeNumber(epData.getEpisodeNumber() != null ? epData.getEpisodeNumber() : 1);
                    episode.setTitle(epData.getTitle() != null ? epData.getTitle() : "Episode " + epData.getEpisodeNumber());
                    episodeRepository.save(episode);
                }
            }
        } catch (Exception e) {}
        sleepToAvoidRateLimit();
    }

    private void fetchReviews(Anime anime, List<User> users) {
        try {
            String revUrl = JIKAN_BASE_URL + "/anime/" + anime.getId() + "/reviews";
            JikanReviewResponse revResp = restTemplate.getForObject(revUrl, JikanReviewResponse.class);
            if (revResp != null && revResp.getData() != null) {
                int userIndex = 0;
                for (JikanReviewResponse.ReviewData revData : revResp.getData()) {
                    if (userIndex >= users.size()) break;
                    Review review = new Review();
                    review.setAnime(anime);
                    review.setUser(users.get(userIndex));
                    review.setScore(revData.getScore() != null ? revData.getScore() : 8);

                    String comment = revData.getComment();
                    if (comment != null && comment.length() > 500) comment = comment.substring(0, 500);
                    review.setComment(comment);
                    review.setCreatedAt(LocalDateTime.now());

                    reviewRepository.save(review);
                    userIndex++;
                }
            }
        } catch (Exception e) {}
        sleepToAvoidRateLimit();
    }

    private List<User> seedUsers() {
        List<User> users = new ArrayList<>();
        String[] usernames = {"otaku_peru", "luffy_king", "skynet_anime"};
        for (String username : usernames) {
            User user = userRepository.findByUsername(username).orElseGet(() -> {
                User newUser = new User();
                newUser.setUsername(username);
                newUser.setEmail(username + "@gahramheit.com");
                newUser.setPassword("password123");
                newUser.setRole(Role.USER);
                return userRepository.save(newUser);
            });
            users.add(user);
        }
        return users;
    }

    private void sleepToAvoidRateLimit() {
        try {
            Thread.sleep(1250); // Mínimo 1.25s para que no se bloquee
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
}