package com.example.gahramheit.service;

import com.example.gahramheit.entity.Badge;
import com.example.gahramheit.repository.BadgeRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BadgeService {

    private final BadgeRepository badgeRepository;

    @PostConstruct
    @Transactional
    public void seedBadges() {
        if (badgeRepository.count() > 0) return;

        List<Badge> defaults = List.of(
                Badge.builder().name("Nuevo en el Mundo Anime").description("Completar menos de 5 animes").conditionKey("COMPLETED < 5").imageUrl("/badges/nuevo.png").tier(1).build(),
                Badge.builder().name("Otaku en Formación").description("Completar 5 o más animes").conditionKey("COMPLETED >= 5").imageUrl("/badges/formacion.png").tier(1).build(),
                Badge.builder().name("Otaku Experimentado").description("Completar 15 o más animes").conditionKey("COMPLETED >= 15").imageUrl("/badges/experimentado.png").tier(2).build(),
                Badge.builder().name("Dios del Anime").description("Completar 30 o más animes").conditionKey("COMPLETED >= 30").imageUrl("/badges/dios.png").tier(3).build(),
                Badge.builder().name("Maratonista Épico").description("Ver 500 o más episodios").conditionKey("EPISODES >= 500").imageUrl("/badges/maratonista.png").tier(2).build()
        );

        badgeRepository.saveAll(defaults);
        log.info("Badges por defecto insertados: {} registros", defaults.size());
    }

    public String evaluateBadge(Long completedCount, int totalEpisodes) {
        List<Badge> badges = badgeRepository.findAll();

        Badge best = null;
        for (Badge badge : badges) {
            if (meetsCondition(badge.getConditionKey(), completedCount, totalEpisodes)) {
                best = badge;
            }
        }

        return best != null ? best.getName() : "Nuevo en el Mundo Anime";
    }

    private boolean meetsCondition(String conditionKey, Long completedCount, int totalEpisodes) {
        return switch (conditionKey) {
            case "COMPLETED < 5" -> completedCount < 5;
            case "COMPLETED >= 5" -> completedCount >= 5;
            case "COMPLETED >= 15" -> completedCount >= 15;
            case "COMPLETED >= 30" -> completedCount >= 30;
            case "EPISODES >= 500" -> totalEpisodes >= 500;
            default -> false;
        };
    }
}
