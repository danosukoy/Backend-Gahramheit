package com.example.gahramheit.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Slf4j
@Service
public class AIService {

    private final Random random = new Random();

    public String generateOtakuProfile(String username, String topGenre, Double avgScore,
                                       Integer totalEpisodes, Long completedCount, Integer year,
                                       List<String> top5Animes) {
        log.info("Generando perfil IA para {} del año {}", username, year);

        String personality = "";
        if (avgScore >= 9.0) {
            personality = pickRandom(
                    "Eres sumamente generoso y amas todo lo que ves.",
                    "Tienes un corazón enorme para dar oportunidades a cualquier anime.",
                    "Eres de esos otakus que hasta lo malo lo disfrutan con pasión."
            );
        } else if (avgScore >= 7.0) {
            personality = pickRandom(
                    "Tienes un gusto equilibrado y sabes apreciar una buena historia.",
                    "Sabes distinguir entre una joya y lo que no vale la pena.",
                    "Tu criterio es sólido, ni muy benevolente ni muy cruel."
            );
        } else {
            personality = pickRandom(
                    "Eres un crítico implacable y muy difícil de complacer (nivel Dios).",
                    "Tus estándares son tan altos que pocos animes los superan.",
                    "Exigente al máximo, solo lo mejor merece tu tiempo."
            );
        }

        String top5Part = "";
        if (top5Animes != null && !top5Animes.isEmpty()) {
            top5Part = " Tus animes mejor calificados fueron: " + String.join(", ", top5Animes) + ".";
        }

        return String.format(
                "¡Saludos %s! Este %d has demostrado ser un verdadero fanático del género %s. " +
                        "Viste %d episodios de %d animes distintos, " +
                        "y tu calificación promedio fue de %.1f/10, lo que nos dice algo muy claro: %s%s " +
                        "¡Sigue explorando nuevos mundos el próximo año!",
                username,
                year,
                topGenre != null ? topGenre : "Misterio",
                totalEpisodes,
                completedCount,
                avgScore,
                personality,
                top5Part
        );
    }

    private String pickRandom(String... options) {
        return options[random.nextInt(options.length)];
    }
}
