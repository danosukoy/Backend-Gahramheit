package com.example.gahramheit.service;

import com.example.gahramheit.dto.GenreDTO;
import com.example.gahramheit.entity.Genre;
import com.example.gahramheit.exception.DuplicateResourceException;
import com.example.gahramheit.exception.ResourceNotFoundException;
import com.example.gahramheit.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GenreService {

    private final GenreRepository genreRepository;
    private final ModelMapper modelMapper;

    public List<GenreDTO> getAllGenres() {
        return genreRepository.findAll().stream()
                .map(genre -> modelMapper.map(genre, GenreDTO.class))
                .collect(Collectors.toList());
    }

    public GenreDTO getGenreById(Long id) {
        Genre genre = genreRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Genre not found with id: " + id));
        return modelMapper.map(genre, GenreDTO.class);
    }

    public GenreDTO createGenre(GenreDTO request) {
        if (genreRepository.findByName(request.getName()).isPresent()) {
            throw new DuplicateResourceException("Genre already exists: " + request.getName());
        }

        Genre genre = Genre.builder()
                .name(request.getName())
                .build();

        genreRepository.save(genre);
        return modelMapper.map(genre, GenreDTO.class);
    }

    public GenreDTO updateGenre(Long id, GenreDTO request) {
        Genre genre = genreRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Genre not found with id: " + id));

        genre.setName(request.getName());
        genreRepository.save(genre);
        return modelMapper.map(genre, GenreDTO.class);
    }

    public void deleteGenre(Long id) {
        Genre genre = genreRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Genre not found with id: " + id));
        genreRepository.delete(genre);
    }
}
