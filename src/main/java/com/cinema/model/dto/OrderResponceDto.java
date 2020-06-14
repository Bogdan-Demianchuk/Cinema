package com.cinema.model.dto;

import java.time.LocalDateTime;
import java.util.List;

public class OrderResponceDto {
    private List<TicketResponceDto> ticketResponceDto;
    private LocalDateTime orderDate;
    private Long userId;

    public List<TicketResponceDto> getTicketResponceDto() {
        return ticketResponceDto;
    }

    public void setTicketResponceDto(List<TicketResponceDto> ticketResponceDto) {
        this.ticketResponceDto = ticketResponceDto;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
