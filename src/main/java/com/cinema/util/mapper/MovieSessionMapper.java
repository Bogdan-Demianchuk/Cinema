package com.cinema.util.mapper;

import com.cinema.model.MovieSession;
import com.cinema.model.dto.MovieSessionRequestDto;
import com.cinema.model.dto.MovieSessionResponceDto;
import com.cinema.service.CinemaHallService;
import com.cinema.service.MovieService;
import org.springframework.stereotype.Component;

@Component
public class MovieSessionMapper {
    private final MovieService movieService;
    private final CinemaHallService cinemaHallService;

    public MovieSessionMapper(MovieService movieService, CinemaHallService cinemaHallService) {
        this.movieService = movieService;
        this.cinemaHallService = cinemaHallService;
    }

    public MovieSessionResponceDto parsingMovieSessionToDto(MovieSession movieSession) {
        MovieSessionResponceDto movieSessionResponceDto = new MovieSessionResponceDto();
        movieSessionResponceDto.setMovieId(movieSession.getMovie().getId());
        movieSessionResponceDto.setCinemaHallId(movieSession.getCinemaHall().getId());
        movieSessionResponceDto.setShowTime(movieSession.getShowTime());
        return movieSessionResponceDto;
    }

    public MovieSession parsingDtoToMovieSession(MovieSessionRequestDto movieSessionRequestDto) {
        MovieSession movieSession = new MovieSession();
        movieSession.setShowTime(movieSessionRequestDto.getShowTime());
        movieSession.setMovie(movieService.getById(movieSessionRequestDto.getMovieId()));
        movieSession.setCinemaHall(cinemaHallService
                .getById(movieSessionRequestDto.getCinemaHallId()));
        return movieSession;
    }
}
