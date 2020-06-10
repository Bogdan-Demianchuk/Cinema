package com.cinema.controller;

import com.cinema.model.Movie;
import com.cinema.model.dto.MovieRequestDto;
import com.cinema.model.dto.MovieResponceDto;
import com.cinema.service.MovieService;
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

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public List<MovieResponceDto> getAll(){
        return movieService.getAll().stream()
                .map(this::parsingMovieToDto)
                .collect(Collectors.toList());
    }
    @PostMapping
    public void add(@RequestBody MovieRequestDto movieRequestDto){
        movieService.add(parsingDtoToMovie(movieRequestDto));
    }

    private MovieResponceDto parsingMovieToDto(Movie movie){
        MovieResponceDto movieResponceDto = new MovieResponceDto();
        movieResponceDto.setTitle(movie.getTitle());
        movieResponceDto.setDescription(movie.getDescription());
        return movieResponceDto;
    }

    private Movie parsingDtoToMovie(MovieRequestDto movieRequestDto){
        Movie movie = new Movie();
        movie.setTitle(movieRequestDto.getTitle());
        movie.setDescription(movieRequestDto.getDescription());
        return movie;
    }
}
