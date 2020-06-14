package com.cinema.controller;

import com.cinema.model.dto.MovieRequestDto;
import com.cinema.model.dto.MovieResponceDto;
import com.cinema.service.MovieService;
import com.cinema.util.mapper.MovieMapper;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class MovieController {
    private final MovieService movieService;
    private final MovieMapper movieMapper;

    public MovieController(MovieService movieService, MovieMapper movieMapper) {
        this.movieService = movieService;
        this.movieMapper = movieMapper;
    }

    @GetMapping
    public List<MovieResponceDto> getAll() {
        return movieService.getAll().stream()
                .map(movieMapper::parsingMovieToDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    public void add(@RequestBody MovieRequestDto movieRequestDto) {
        movieService.add(movieMapper.parsingDtoToMovie(movieRequestDto));
    }

}
