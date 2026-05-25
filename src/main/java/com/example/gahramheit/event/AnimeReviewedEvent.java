package com.example.gahramheit.event;

import lombok.Getter;

@Getter
public class AnimeReviewedEvent {
    private final Long animeId;
    private final Integer score;

    public AnimeReviewedEvent(Long animeId, Integer score) {
        this.animeId = animeId;
        this.score = score;
    }
}