package com.cinema.model.dto;

import java.util.List;

public class ShoppingCartResponceDto {
    private Long userId;
    private List<TicketResponceDto> ticketResponceDtos;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<TicketResponceDto> getTicketResponceDtos() {
        return ticketResponceDtos;
    }

    public void setTicketResponceDtos(List<TicketResponceDto> ticketResponceDtos) {
        this.ticketResponceDtos = ticketResponceDtos;
    }
}
