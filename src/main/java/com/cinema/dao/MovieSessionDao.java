package com.cinema.dao;

import com.cinema.model.MovieSession;
import java.time.LocalDate;
import java.util.List;

public interface MovieSessionDao {
    MovieSession add(MovieSession movieSession);

    List<MovieSession> getAll();

    List<MovieSession> findAvailableSessions(Long movieId, LocalDate date);

    MovieSession findSessionsById(Long id);
}
