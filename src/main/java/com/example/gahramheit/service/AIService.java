package com.example.gahramheit.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AIService {

    /**
     * Genera un mensaje personalizado basado en las estadísticas del usuario.
     * En el futuro, aquí se inyectará la llamada real a una API de IA.
     */
    public String generateOtakuProfile(String username, String topGenre, Double avgScore) {
        log.info("Llamando a la IA para generar el perfil de {}", username);

        // Lógica de personalidad simulada basada en la nota
        String personality = "";
        if (avgScore >= 9.0) {
            personality = "Eres sumamente generoso y amas todo lo que ves.";
        } else if (avgScore >= 7.0) {
            personality = "Tienes un gusto equilibrado y sabes apreciar una buena historia.";
        } else {
            personality = "Eres un crítico implacable y muy difícil de complacer (nivel Dios).";
        }

        // Construcción del mensaje final simulado
        return String.format(
                "¡Saludos %s! Este 2024 has demostrado ser un verdadero fanático del género %s. " +
                        "Tu calificación promedio fue de %.1f/10, lo que nos dice algo muy claro: %s " +
                        "¡Sigue explorando nuevos mundos el próximo año!",
                username,
                topGenre != null ? topGenre : "Misterio",
                avgScore,
                personality
        );
    }
}