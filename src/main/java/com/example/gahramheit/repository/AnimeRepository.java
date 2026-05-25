package com.example.gahramheit.repository;

import com.example.gahramheit.entity.Anime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimeRepository extends JpaRepository<Anime, Long> {
    List<Anime> findByTitleContainingIgnoreCase(String title);
    @org.springframework.data.jpa.repository.Query(
            value = "SELECT a.genre FROM user_anime_list ual " +
                    "JOIN animes a ON ual.anime_id = a.id " +
                    "WHERE ual.user_id = :userId AND a.genre IS NOT NULL " +
                    "GROUP BY a.genre " +
                    "ORDER BY COUNT(a.genre) DESC LIMIT 1",
            nativeQuery = true)
    String getMostWatchedGenreByUser(@org.springframework.data.repository.query.Param("userId") Long userId);

}

