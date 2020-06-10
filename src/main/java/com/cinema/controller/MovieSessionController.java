package com.cinema.controller;

import com.cinema.model.MovieSession;
import com.cinema.model.dto.MovieSessionRequestDto;
import com.cinema.model.dto.MovieSessionResponceDto;
import com.cinema.service.CinemaHallService;
import com.cinema.service.MovieService;
import com.cinema.service.MovieSessionService;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/moviesessions")
public class MovieSessionController {
    private final MovieSessionService movieSessionService;
    private final MovieService movieService;
    private final CinemaHallService cinemaHallService;

    public MovieSessionController(MovieSessionService movieSessionService, MovieService movieService, CinemaHallService cinemaHallService) {
        this.movieSessionService = movieSessionService;
        this.movieService = movieService;
        this.cinemaHallService = cinemaHallService;
    }

    @PostMapping
    public void add(@RequestBody MovieSessionRequestDto movieSessionRequestDto) {
        MovieSession movieSession = parsingDtoToMovieSession(movieSessionRequestDto);
        movieSessionService.add(movieSession);
    }

    @GetMapping("/available")
    public List<MovieSessionResponceDto> getAvailableMovieSession
            (@RequestParam Long movieId, @RequestParam LocalDate showTime) {
        List<MovieSession> availableSessions = movieSessionService.findAvailableSessions(movieId, showTime);
        return availableSessions.stream()
                .map(this::parsingMovieSessionToDto)
                .collect(Collectors.toList());
    }

    private MovieSessionResponceDto parsingMovieSessionToDto(MovieSession movieSession) {
        MovieSessionResponceDto movieSessionResponceDto = new MovieSessionResponceDto();
        movieSessionResponceDto.setMovieId(movieSession.getMovie().getId());
        movieSessionResponceDto.setCinemaHallId(movieSession.getCinemaHall().getId());
        movieSessionResponceDto.setShowTime(movieSession.getShowTime());
        return movieSessionResponceDto;
    }

    private MovieSession parsingDtoToMovieSession
            (MovieSessionRequestDto movieSessionRequestDto) {
        MovieSession movieSession = new MovieSession();
        movieSession.setShowTime(movieSessionRequestDto.getShowTime());
        movieSession.setMovie(movieService.getById
                (movieSessionRequestDto.getMovieId()));
        movieSession.setCinemaHall(cinemaHallService.getById
                (movieSessionRequestDto.getCinemaHallId()));
        return movieSession;
    }
}
