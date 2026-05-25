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

    // Relación con el usuario. LAZY evita que traiga toda la info del usuario si no la pides.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "recap_year", nullable = false)
    private Integer year;

    @Column(name = "total_genres_rated")
    private Integer totalGenresRated;

    @Column(name = "top_genre", length = 50)
    private String topGenre;

    // Puedes guardar el top 5 como un string JSON ligero
    @Column(name = "top_5_animes", columnDefinition = "TEXT")
    private String top5Animes;

    @Column(name = "average_score")
    private Double averageScore;

    // Aquí se guardará el mensaje generado por la IA
    @Column(name = "ai_personalized_message", columnDefinition = "TEXT")
    private String aiPersonalizedMessage;
}