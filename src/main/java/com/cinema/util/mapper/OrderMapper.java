package com.cinema.util.mapper;

import com.cinema.model.Order;
import com.cinema.model.dto.OrderResponceDto;
import com.cinema.service.MovieSessionService;
import com.cinema.service.UserService;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    private final UserService userService;
    private final MovieSessionService movieSessionService;
    private final TicketMapper ticketMapper;

    public OrderMapper(UserService userService,
                       MovieSessionService movieSessionService,
                       TicketMapper ticketMapper) {
        this.userService = userService;
        this.movieSessionService = movieSessionService;
        this.ticketMapper = ticketMapper;
    }

    public OrderResponceDto parsingOrderToDto(Order order) {
        OrderResponceDto orderResponceDto = new OrderResponceDto();
        orderResponceDto.setOrderDate(order.getOrderDate());
        orderResponceDto.setUserId(order.getUser().getId());
        orderResponceDto.setTicketResponceDto(order.getTickets().stream()
                .map(ticketMapper::parsingTicketToDto)
                .collect(Collectors.toList()));
        return orderResponceDto;
    }
}
