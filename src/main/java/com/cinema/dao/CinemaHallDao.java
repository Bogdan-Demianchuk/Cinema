package com.cinema.dao;

import com.cinema.model.CinemaHall;
import com.cinema.model.Movie;
import java.util.List;

public interface CinemaHallDao {
    CinemaHall add(CinemaHall cinemaHall);

    List<CinemaHall> getAll();

    CinemaHall getById(Long id);
}
