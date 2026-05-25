package com.example.gahramheit.controller;

import com.example.gahramheit.dto.EpisodeDTO;
import com.example.gahramheit.service.EpisodeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class EpisodeController {

    private final EpisodeService episodeService;

    @GetMapping("/anime/{animeId}/episodes")
    public ResponseEntity<List<EpisodeDTO>> getEpisodesByAnime(@PathVariable Long animeId) {
        return ResponseEntity.ok(episodeService.getEpisodesByAnime(animeId));
    }

    @GetMapping("/episodes/{id}")
    public ResponseEntity<EpisodeDTO> getEpisodeById(@PathVariable Long id) {
        return ResponseEntity.ok(episodeService.getEpisodeById(id));
    }

    @PostMapping("/anime/{animeId}/episodes")
    public ResponseEntity<EpisodeDTO> createEpisode(
            @PathVariable Long animeId,
            @Valid @RequestBody EpisodeDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(episodeService.createEpisode(animeId, request));
    }
}
