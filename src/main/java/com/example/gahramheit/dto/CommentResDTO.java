package com.example.gahramheit.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentResDTO {
    private Long id;
    private Long animeId;
    private Long userId;
    private String username;
    private String content;
    private Long parentId;
    private int likesCount;
    private int dislikesCount;
    private LocalDateTime createdAt;
    private boolean hasReplies;
}
