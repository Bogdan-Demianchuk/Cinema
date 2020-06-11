package com.cinema.controller;

import com.cinema.model.dto.CinemaHallRequestDto;
import com.cinema.model.dto.CinemaHallResponceDto;
import com.cinema.service.CinemaHallService;
import com.cinema.util.mapper.CinemaHallMapper;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cinemahalls")
public class CinemaHallController {
    private final CinemaHallService cinemaHallService;
    private final CinemaHallMapper cinemaHallMapper;

    public CinemaHallController(CinemaHallService cinemaHallService,
                                CinemaHallMapper cinemaHallMapper) {
        this.cinemaHallService = cinemaHallService;
        this.cinemaHallMapper = cinemaHallMapper;
    }

    @PostMapping
    public void add(@RequestBody CinemaHallRequestDto cinemaHallRequestDto) {
        cinemaHallService.add(cinemaHallMapper.parsingDtoToCinemaHall(cinemaHallRequestDto));
    }

    @GetMapping
    public List<CinemaHallResponceDto> getAll() {
        return cinemaHallService.getAll().stream()
                .map(cinemaHallMapper::parsingCinemaHallToDto)
                .collect(Collectors.toList());
    }
}
