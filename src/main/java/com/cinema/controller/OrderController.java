package com.cinema.controller;

import com.cinema.model.ShoppingCart;
import com.cinema.model.User;
import com.cinema.model.dto.OrderResponceDto;
import com.cinema.service.OrderService;
import com.cinema.service.ShoppingCartService;
import com.cinema.service.UserService;
import com.cinema.util.mapper.OrderMapper;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final ShoppingCartService shoppingCartService;
    private final UserService userService;
    private final OrderService orderService;
    private final OrderMapper orderMapper;

    public OrderController(ShoppingCartService shoppingCartService,
                           UserService userService, OrderService orderService,
                           OrderMapper orderMapper) {
        this.shoppingCartService = shoppingCartService;
        this.userService = userService;
        this.orderService = orderService;
        this.orderMapper = orderMapper;
    }

    @PostMapping("/complete")
    public void completeOrder(Authentication authentication) {
        User user = userService.findByEmail(((UserDetails) authentication.getPrincipal())
                .getUsername()).get();
        ShoppingCart userShoppingCart = shoppingCartService.getByUser(user);
        orderService.completeOrder(userShoppingCart.getTickets(), user);
    }

    @GetMapping
    public List<OrderResponceDto> getOrderHistory(Authentication authentication) {
        User user = userService.findByEmail(((UserDetails)
                authentication.getPrincipal())
                .getUsername()).get();
        return orderService.getOrderHistory(user).stream()
                .map(orderMapper::parsingOrderToDto)
                .collect(Collectors.toList());
    }
}
