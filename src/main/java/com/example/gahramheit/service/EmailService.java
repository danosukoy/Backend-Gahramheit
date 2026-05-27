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

            String htmlContent = """
    <html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body style="margin: 0; padding: 0; background-color: #0F172A; font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; color: #E2E8F0;">
        <table role="presentation" width="100%%" cellspacing="0" cellpadding="0" border="0" style="background-color: #0F172A; padding: 40px 20px;">
            <tr>
                <td align="center">
                    <table role="presentation" width="100%%" style="max-width: 600px; background-color: #1E293B; border-radius: 16px; padding: 32px; text-align: left;" cellspacing="0" cellpadding="0" border="0">
                        <tr>
                            <td>
                                <h2 style="color: #00E5FF; font-size: 24px; margin-top: 0; margin-bottom: 16px; font-weight: 700;">
                                    ¡Hola, %s! 👋
                                </h2>
                                <p style="font-size: 16px; line-height: 1.6; margin-bottom: 12px;">
                                    ¡Gracias por unirte a la fogata de <strong>Gahramheit</strong> OwO! 🔥
                                </p>
                                <p style="font-size: 15px; line-height: 1.6; color: #94A3B8; margin-bottom: 24px;">
                                    A partir de ahora podrás armar tu propia lista de animes, registrar tus episodios vistos, dejar tus reseñas de forma implacable y participar en el foro global. ¿Listo para esta aventura? 🚀
                                </p>
                                <hr style="border: 0; border-top: 1px solid #334155; margin-bottom: 24px;">
                                <p style="font-size: 14px; color: #64748B; margin: 0; font-style: italic;">
                                    - El equipo de Gahramheit
                                </p>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
        </table>
    </body>
    </html>
    """.formatted(username);

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