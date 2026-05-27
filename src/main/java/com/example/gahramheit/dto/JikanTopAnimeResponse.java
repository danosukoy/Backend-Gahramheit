package com.example.gahramheit.dto;

import lombok.Data;
import java.util.List;

@Data
public class JikanTopAnimeResponse {
    private List<AnimeData> data;

    @Data
    public static class AnimeData {
        private Long mal_id;
        private String title;
        private Integer episodes;
        private String status;
        private String synopsis;
        private Integer year;
        private Images images;
        private List<Studio> studios;
        private List<GenreDto> genres;
    }

    @Data
    public static class Images {
        private Jpg jpg;
    }

    @Data
    public static class Jpg {
        private String image_url;
    }

    @Data
    public static class Studio {
        private String name;
    }

    @Data
    public static class GenreDto {
        private Long mal_id;
        private String name;
    }
}