package com.cinema.model.dto;

public class TicketResponceDto {
    private MovieSessionResponceDto movieSessionResponceDto;
    private Long userId;

    public MovieSessionResponceDto getMovieSessionResponceDto() {
        return movieSessionResponceDto;
    }

    public void setMovieSessionResponceDto(MovieSessionResponceDto movieSessionResponceDto) {
        this.movieSessionResponceDto = movieSessionResponceDto;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
