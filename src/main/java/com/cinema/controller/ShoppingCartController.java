package com.cinema.controller;

import com.cinema.model.dto.ShoppingCartResponceDto;
import com.cinema.service.MovieSessionService;
import com.cinema.service.ShoppingCartService;
import com.cinema.service.UserService;
import com.cinema.util.mapper.ShoppingCartMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shoppingcarts")
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;
    private final MovieSessionService movieSessionService;
    private final UserService userService;
    private final ShoppingCartMapper shoppingCartMapper;

    public ShoppingCartController(ShoppingCartService shoppingCartService,
                                  MovieSessionService movieSessionService,
                                  UserService userService, ShoppingCartMapper shoppingCartMapper) {
        this.shoppingCartService = shoppingCartService;
        this.movieSessionService = movieSessionService;
        this.userService = userService;
        this.shoppingCartMapper = shoppingCartMapper;
    }

    @PostMapping("/addmoviesession")
    public void addMovieSessionToCart(@RequestBody Long movieSessionId,
                                      @RequestParam Long userId) {
        shoppingCartService.addSession(movieSessionService.findSessionsById(movieSessionId),
                        userService.getById(userId));
    }

    @GetMapping("/byuser")
    public ShoppingCartResponceDto getByUser(@RequestParam Long id) {
        return shoppingCartMapper.parsingShoppingCartToDto(shoppingCartService
                .getByUser(userService.getById(id)));
    }
}
