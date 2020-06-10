package com.cinema.controller;

import com.cinema.model.CinemaHall;
import com.cinema.model.dto.CinemaHallRequestDto;
import com.cinema.model.dto.CinemaHallResponceDto;
import com.cinema.service.CinemaHallService;
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

    public CinemaHallController(CinemaHallService cinemaHallService) {
        this.cinemaHallService = cinemaHallService;
    }

    @PostMapping
    public void add(@RequestBody CinemaHallRequestDto cinemaHallRequestDto) {
        cinemaHallService.add(parsingDtoToCinemaHall(cinemaHallRequestDto));
    }

    @GetMapping
    public List<CinemaHallResponceDto> getAll() {
        return cinemaHallService.getAll().stream()
                .map(this::parsingCinemaHallToDto)
                .collect(Collectors.toList());
    }

    private CinemaHall parsingDtoToCinemaHall(CinemaHallRequestDto cinemaHallRequestDto) {
        CinemaHall cinemaHall = new CinemaHall();
        cinemaHall.setCapacity(cinemaHallRequestDto.getCapacity());
        cinemaHall.setDescription(cinemaHallRequestDto.getDescription());
        return  cinemaHall;
    }

    private CinemaHallResponceDto parsingCinemaHallToDto(CinemaHall cinemaHall) {
        CinemaHallResponceDto cinemaHallResponceDto = new CinemaHallResponceDto();
        cinemaHallResponceDto.setCapacity(cinemaHall.getCapacity());
        cinemaHallResponceDto.setDescription(cinemaHall.getDescription());
        cinemaHallResponceDto.setId(cinemaHall.getId());
        return cinemaHallResponceDto;
    }
}
