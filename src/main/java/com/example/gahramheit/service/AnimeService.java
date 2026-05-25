package com.example.gahramheit.service;

import com.example.gahramheit.dto.AnimeDTO;
import com.example.gahramheit.dto.AnimeDetailResDTO;
import com.example.gahramheit.entity.Anime;
import com.example.gahramheit.exception.ResourceNotFoundException;
import com.example.gahramheit.repository.AnimeRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AnimeService {

    private final AnimeRepository animeRepository;
    private final ModelMapper modelMapper;

    public Page<AnimeDTO> getAnimeCatalog(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Anime> animesPage = animeRepository.findAll(pageable);
        return animesPage.map(this::toCardDto);
    }

    public List<AnimeDTO> searchAnimesByTitle(String keyword) {
        List<Anime> animes = animeRepository.findByTitleContainingIgnoreCase(keyword);
        return animes.stream()
                .map(this::toCardDto)
                .collect(Collectors.toList());
    }

    public AnimeDetailResDTO getAnimeDetails(Long animeId) {
        Anime anime = animeRepository.findById(animeId)
                .orElseThrow(() -> new ResourceNotFoundException("Anime not found with id: " + animeId));

        AnimeDetailResDTO dto = modelMapper.map(anime, AnimeDetailResDTO.class);
        dto.setGenres(anime.getGenres().stream()
                .map(g -> g.getName())
                .collect(Collectors.toList()));
        dto.setActoresVoz(anime.getActoresVoz() != null ? anime.getActoresVoz() : new ArrayList<>());
        return dto;
    }

    private AnimeDTO toCardDto(Anime anime) {
        AnimeDTO dto = modelMapper.map(anime, AnimeDTO.class);
        dto.setGenreNames(anime.getGenres().stream()
                .map(g -> g.getName())
                .collect(Collectors.toSet()));
        return dto;
    }
}
