package com.example.gahramheit.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    public void sendWelcomeEmail(String toEmail, String username) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(toEmail);
            helper.setSubject("¡Bienvenido a Gahramheit, " + username + "! 🎬");

            String htmlContent = "<html><body>"
                    + "<h2 style='color: #00E5FF;'>¡Hola, " + username + "!</h2>"
                    + "<p>Gracias por unirte a la fogata de Gahramheit OwO!.</p>"
                    + "<p>A partir de ahora podrás armar tu propia lista de animes, registrar tus episodios vistos, "
                    + "dar tus reseñas de forma implacable y participar en el foro global, listo para esta aventura?.</p>"
                    + "<br><p><i>- El equipo de Gahramheit</i></p>"
                    + "</body></html>";

            helper.setText(htmlContent, true);

            mailSender.send(message);
            log.info("📧 Correo de bienvenida enviado con éxito a: {}", toEmail);

        } catch (MessagingException e) {
            log.error("Error crítico al estructurar el correo para: {}", toEmail, e);
        } catch (Exception e) {
            log.error("No se pudo conectar al servidor SMTP para enviar el correo", e);
        }
    }
}