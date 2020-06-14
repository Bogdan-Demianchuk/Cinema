package com.cinema.util.mapper;

import com.cinema.model.Ticket;
import com.cinema.model.dto.TicketResponceDto;
import org.springframework.stereotype.Component;

@Component
public class TicketMapper {
    private final MovieSessionMapper movieSessionMapper;

    public TicketMapper(MovieSessionMapper movieSessionMapper) {
        this.movieSessionMapper = movieSessionMapper;
    }

    public TicketResponceDto parsingTicketToDto(Ticket ticket) {
        TicketResponceDto ticketResponceDto = new TicketResponceDto();
        ticketResponceDto.setUserId(ticket.getUser().getId());
        ticketResponceDto.setMovieSessionResponceDto(movieSessionMapper
                .parsingMovieSessionToDto(ticket.getMovieSession()));
        return ticketResponceDto;
    }
}
