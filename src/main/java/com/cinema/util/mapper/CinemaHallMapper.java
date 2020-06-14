package com.cinema.util.mapper;

import com.cinema.model.CinemaHall;
import com.cinema.model.dto.CinemaHallRequestDto;
import com.cinema.model.dto.CinemaHallResponceDto;
import org.springframework.stereotype.Component;

@Component
public class CinemaHallMapper {

    public CinemaHall parsingDtoToCinemaHall(CinemaHallRequestDto cinemaHallRequestDto) {
        CinemaHall cinemaHall = new CinemaHall();
        cinemaHall.setCapacity(cinemaHallRequestDto.getCapacity());
        cinemaHall.setDescription(cinemaHallRequestDto.getDescription());
        return cinemaHall;
    }

    public CinemaHallResponceDto parsingCinemaHallToDto(CinemaHall cinemaHall) {
        CinemaHallResponceDto cinemaHallResponceDto = new CinemaHallResponceDto();
        cinemaHallResponceDto.setCapacity(cinemaHall.getCapacity());
        cinemaHallResponceDto.setDescription(cinemaHall.getDescription());
        cinemaHallResponceDto.setId(cinemaHall.getId());
        return cinemaHallResponceDto;
    }
}
