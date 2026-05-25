package com.example.gahramheit.service;

import com.example.gahramheit.dto.ReviewCreateReqDTO;
import com.example.gahramheit.dto.ReviewResDTO;
import com.example.gahramheit.entity.Anime;
import com.example.gahramheit.entity.Review;
import com.example.gahramheit.entity.User;
import com.example.gahramheit.exception.ResourceNotFoundException;
import com.example.gahramheit.repository.AnimeRepository;
import com.example.gahramheit.repository.ReviewRepository;
import com.example.gahramheit.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final AnimeRepository animeRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public ReviewResDTO createReview(Long userId, ReviewCreateReqDTO request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        Anime anime = animeRepository.findById(request.getAnimeId())
                .orElseThrow(() -> new ResourceNotFoundException("Anime not found with id: " + request.getAnimeId()));

        Review review = Review.builder()
                .user(user)
                .anime(anime)
                .score(request.getScore())
                .comment(request.getComment())
                .createdAt(LocalDateTime.now())
                .build();

        reviewRepository.save(review);
        return toDto(review);
    }

    public List<ReviewResDTO> getReviewsByAnime(Long animeId) {
        if (!animeRepository.existsById(animeId)) {
            throw new ResourceNotFoundException("Anime not found with id: " + animeId);
        }

        return reviewRepository.findByAnimeId(animeId)
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public ReviewResDTO getReviewById(Long id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Review not found with id: " + id));
        return toDto(review);
    }

    public void deleteReview(Long id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Review not found with id: " + id));
        reviewRepository.delete(review);
    }

    private ReviewResDTO toDto(Review review) {
        ReviewResDTO dto = modelMapper.map(review, ReviewResDTO.class);
        dto.setUsername(review.getUser().getUsername());
        return dto;
    }
}
