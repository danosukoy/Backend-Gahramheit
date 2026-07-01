package com.example.gahramheit.service;

import com.example.gahramheit.entity.User;
import com.example.gahramheit.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.Year;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class WrappedScheduler {

    private final WrappedService wrappedService;
    private final UserRepository userRepository;

    @Scheduled(cron = "0 0 0 1 1 *")
    @Transactional
    public void generateAnnualRecaps() {
        List<User> users = userRepository.findAll();
        int currentYear = Year.now().getValue();
        int previousYear = currentYear - 1;

        log.info("Iniciando generación automática de recaps para el año {}", previousYear);

        for (User user : users) {
            try {
                wrappedService.generateRecap(user.getId(), previousYear);
            } catch (Exception e) {
                log.error("Error generando recap para usuario {}: {}", user.getId(), e.getMessage());
            }
        }

        log.info("Recaps generados para {} usuarios", users.size());
    }
}
