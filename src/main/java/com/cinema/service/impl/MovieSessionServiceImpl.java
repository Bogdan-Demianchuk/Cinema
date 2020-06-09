package com.cinema.service.impl;

import com.cinema.dao.MovieSessionDao;
import com.cinema.model.MovieSession;
import com.cinema.service.MovieSessionService;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class MovieSessionServiceImpl implements MovieSessionService {
    private final MovieSessionDao movieSessionDao;

    public MovieSessionServiceImpl(MovieSessionDao movieSessionDao) {
        this.movieSessionDao = movieSessionDao;
    }

    @Override
    public MovieSession add(MovieSession movieSession) {
        return movieSessionDao.add(movieSession);
    }

    @Override
    public List<MovieSession> findAvailableSessions(Long movieId, LocalDate date) {
        return movieSessionDao.findAvailableSessions(movieId, date);
    }
}
