package com.example.gahramheit.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "anime_id", nullable = false)
    private Long animeId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(name = "parent_id")
    private Long parentId;

    @Column(name = "likes_count", nullable = false)
    private int likesCount = 0;

    @Column(name = "dislikes_count", nullable = false)
    private int dislikesCount = 0;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
}
