package com.cinema.controller;

import com.cinema.model.Order;
import com.cinema.model.User;
import com.cinema.service.OrderService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/complete")
    public void completeOrder(Order order){
        orderService.completeOrder()
    }

    @GetMapping
    public List<OrderResponceDto> getOrderHistory(User user){

    }
}
