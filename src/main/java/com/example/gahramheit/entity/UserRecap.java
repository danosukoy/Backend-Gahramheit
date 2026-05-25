package com.example.gahramheit.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_recaps")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRecap {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "recap_year", nullable = false)
    private Integer year;

    @Column(name = "total_genres_rated")
    private Integer totalGenresRated;

    @Column(name = "top_genre", length = 50)
    private String topGenre;

    @Column(name = "top_5_animes", columnDefinition = "TEXT")
    private String top5Animes;

    @Column(name = "average_score")
    private Double averageScore;

    @Column(name = "ai_personalized_message", columnDefinition = "TEXT")
    private String aiPersonalizedMessage;
}