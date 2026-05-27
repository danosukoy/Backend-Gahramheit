package com.example.gahramheit.controller;

import com.example.gahramheit.service.DataPopulatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final DataPopulatorService dataPopulatorService;

    // Llama a este endpoint pasando la cantidad de páginas en la URL
    // Ejemplo POST: http://localhost:8080/api/admin/sync-top-anime?pagesToSync=10
    @PostMapping("/sync-top-anime")
    public ResponseEntity<String> syncTopAnime(@RequestParam int pagesToSync) {
        if (pagesToSync < 1 || pagesToSync > 50) {
            return ResponseEntity.badRequest().body("❌ El número de páginas debe estar entre 1 y 50.");
        }

        // Ejecutar en segundo plano para que Postman no se quede congelado por el TimeOut
        new Thread(() -> dataPopulatorService.syncTopAnime(pagesToSync)).start();

        return ResponseEntity.ok("🚀 Sincronización automática iniciada. Se procesarán " + pagesToSync
                + " páginas (aprox. " + (pagesToSync * 25) + " animes con sus episodios y reviews). "
                + "Monitorea el progreso en la consola de IntelliJ.");
    }
}