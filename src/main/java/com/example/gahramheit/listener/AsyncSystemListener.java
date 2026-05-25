package com.example.gahramheit.listener;

import com.example.gahramheit.event.AnimeReviewedEvent;
import com.example.gahramheit.event.UserRegisteredEvent;
import com.example.gahramheit.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AsyncSystemListener {
    private final EmailService emailService;
    @Async
    @EventListener
    public void handleUserRegistration(UserRegisteredEvent event) {
        log.info("⚡ [ASYNC-THREAD: {}] Iniciando simulación de envío de correo a: {}",
                Thread.currentThread().getName(), event.getEmail());
        try {
            Thread.sleep(3000);
            log.info("📧 [ASYNC] Correo de bienvenida enviado exitosamente al otaku: {}", event.getUsername());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log.error("Fallo al enviar el correo asíncrono", e);
        }
        emailService.sendWelcomeEmail(event.getEmail(), event.getUsername());
    }

    @Async
    @EventListener
    public void handleAnimeReview(AnimeReviewedEvent event) {
        log.info("⚡ [ASYNC-THREAD: {}] Recalculando estadísticas para el Anime ID: {}",
                Thread.currentThread().getName(), event.getAnimeId());
        try {
            Thread.sleep(1500);
            log.info("📊 [ASYNC] Score promedio actualizado para el Anime ID: {}", event.getAnimeId());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}