package com.example.gahramheit.service;

import com.example.gahramheit.dto.JikanEpisodeResponse;
import com.example.gahramheit.dto.JikanReviewResponse;
import com.example.gahramheit.entity.*;
import com.example.gahramheit.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DataPopulatorService {

    @Autowired private AnimeRepository animeRepository;
    @Autowired private EpisodeRepository episodeRepository;
    @Autowired private ReviewRepository reviewRepository;
    @Autowired private UserRepository userRepository;
    @Autowired private UserAnimeListRepository userAnimeListRepository;

    private final RestTemplate restTemplate = new RestTemplate();
    private final String JIKAN_BASE_URL = "https://api.jikan.moe/v4";

    public void populateRemainingTables() {
        System.out.println("=== INICIANDO SEEDER: LLENANDO EPISODIOS, USERS Y REVIEWS ===");

        List<User> testUsers = createTestUsers();

        List<Anime> existingAnimes = animeRepository.findAll();

        int limit = Math.min(20, existingAnimes.size());

        for (int i = 0; i < limit; i++) {
            Anime anime = existingAnimes.get(i);
            System.out.println("-> Descargando data para: " + anime.getTitle());

            try {
                String epUrl = JIKAN_BASE_URL + "/anime/" + anime.getId() + "/episodes";
                JikanEpisodeResponse epResponse = restTemplate.getForObject(epUrl, JikanEpisodeResponse.class);

                if (epResponse != null && epResponse.getData() != null) {
                    for (JikanEpisodeResponse.EpisodeData epData : epResponse.getData()) {
                        Episode episode = new Episode();
                        episode.setAnime(anime);
                        episode.setEpisodeNumber(epData.getEpisodeNumber() != null ? epData.getEpisodeNumber() : 1);
                        episode.setTitle(epData.getTitle() != null ? epData.getTitle() : "Episode " + epData.getEpisodeNumber());
                        episodeRepository.save(episode);
                    }
                }
            } catch (Exception e) {
                System.out.println("Error crítico en episodios para " + anime.getTitle() + ": " + e.getMessage());
                e.printStackTrace();
            }

            try { Thread.sleep(1500); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }

            try {
                String revUrl = JIKAN_BASE_URL + "/anime/" + anime.getId() + "/reviews";
                JikanReviewResponse revResponse = restTemplate.getForObject(revUrl, JikanReviewResponse.class);

                if (revResponse != null && revResponse.getData() != null) {
                    int userIndex = 0;
                    for (JikanReviewResponse.ReviewData revData : revResponse.getData()) {
                        if (userIndex >= testUsers.size()) break;

                        Review review = new Review();
                        review.setAnime(anime);
                        review.setUser(testUsers.get(userIndex));
                        review.setScore(revData.getScore() != null ? revData.getScore() : 8);

                        String comment = revData.getComment();
                        if(comment != null && comment.length() > 500) comment = comment.substring(0, 500);
                        review.setComment(comment);

                        review.setCreatedAt(LocalDateTime.now());
                        reviewRepository.save(review);
                        userIndex++;
                    }
                }
            } catch (Exception e) {
                System.out.println("Error crítico en reviews para " + anime.getTitle() + ": " + e.getMessage());
                e.printStackTrace();
            }

            try { Thread.sleep(1500); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }

            for (User user : testUsers) {
                if (Math.random() > 0.5) {
                    UserAnimeListId id = new UserAnimeListId(user.getId(), anime.getId());
                    Status randomStatus = Status.values()[(int) (Math.random() * Status.values().length)];

                    if (userAnimeListRepository.existsById(id)) {
                        System.out.println("La lista para el usuario " + user.getUsername() + " y anime " + anime.getTitle() + " ya existe. Saltando...");
                        continue;
                    }

                    UserAnimeList userAnime = new UserAnimeList();
                    userAnime.setId(id);
                    userAnime.setUser(user);
                    userAnime.setAnime(anime);
                    userAnime.setStatus(randomStatus);
                    userAnime.setCurrentEpisode(randomStatus == Status.COMPLETED ? (anime.getEpisodesCount() != null ? anime.getEpisodesCount() : 12) : 1);

                    userAnimeListRepository.save(userAnime);
                }
            }
        }
        System.out.println("=== ¡SEEDER TERMINADO! REVISA TU BASE DE DATOS EN NEON ===");
    }

    private List<User> createTestUsers() {
        List<User> users = new ArrayList<>();
        String[] usernames = {"otaku_peru", "luffy_king", "skynet_anime", "gaara_sand", "goku_super"};

        for (String username : usernames) {
            Optional<User> existing = userRepository.findByUsername(username);
            if (existing.isPresent()) {
                users.add(existing.get());
            } else {
                User newUser = new User();
                newUser.setUsername(username);
                newUser.setEmail(username + "@gahramheit.com");
                newUser.setPassword("password123");
                newUser.setRole(Role.USER);
                users.add(userRepository.save(newUser));
            }
        }
        return users;
    }
}