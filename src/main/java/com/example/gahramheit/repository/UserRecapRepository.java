package com.example.gahramheit.repository;

import com.example.gahramheit.entity.UserRecap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRecapRepository extends JpaRepository<UserRecap, Long> {

    // Método que usará Guillermo después para el Endpoint: "GET /api/recaps/my-recap"
    Optional<UserRecap> findByUserIdAndYear(Long userId, Integer year);

    // Verifica si ya se generó el recap de este año para no duplicarlo por accidente
    boolean existsByUserIdAndYear(Long userId, Integer year);
}