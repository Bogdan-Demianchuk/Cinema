package com.cinema.util.mapper;

import com.cinema.model.Movie;
import com.cinema.model.dto.MovieRequestDto;
import com.cinema.model.dto.MovieResponceDto;
import org.springframework.stereotype.Component;

@Component
public class MovieMapper {

    public MovieResponceDto parsingMovieToDto(Movie movie) {
        MovieResponceDto movieResponceDto = new MovieResponceDto();
        movieResponceDto.setTitle(movie.getTitle());
        movieResponceDto.setDescription(movie.getDescription());
        return movieResponceDto;
    }

    public Movie parsingDtoToMovie(MovieRequestDto movieRequestDto) {
        Movie movie = new Movie();
        movie.setTitle(movieRequestDto.getTitle());
        movie.setDescription(movieRequestDto.getDescription());
        return movie;
    }
}
