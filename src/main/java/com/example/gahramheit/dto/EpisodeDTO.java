package com.example.gahramheit.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EpisodeDTO {
    private Long id;
    private Long animeId;
    private Integer episodeNumber;
    private String title;
}
