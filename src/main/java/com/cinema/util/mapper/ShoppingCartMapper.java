package com.cinema.util.mapper;

import com.cinema.model.ShoppingCart;
import com.cinema.model.dto.ShoppingCartResponceDto;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class ShoppingCartMapper {
    private final TicketMapper ticketMapper;

    public ShoppingCartMapper(TicketMapper ticketMapper) {
        this.ticketMapper = ticketMapper;
    }

    public ShoppingCartResponceDto parsingShoppingCartToDto(ShoppingCart shoppingCart) {
        ShoppingCartResponceDto shoppingCartResponceDto = new ShoppingCartResponceDto();
        shoppingCartResponceDto.setUserId(shoppingCart.getUser().getId());
        shoppingCartResponceDto.setTicketResponceDtos(shoppingCart.getTickets()
                .stream()
                .map(ticketMapper::parsingTicketToDto)
                .collect(Collectors.toList()));
        return shoppingCartResponceDto;
    }
}
