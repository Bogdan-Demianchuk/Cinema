package com.cinema.controller;

import com.cinema.model.CinemaHall;
import com.cinema.model.Movie;
import com.cinema.model.MovieSession;
import com.cinema.model.Role;
import com.cinema.model.User;
import com.cinema.service.AuthenticationService;
import com.cinema.service.CinemaHallService;
import com.cinema.service.MovieService;
import com.cinema.service.MovieSessionService;
import com.cinema.service.OrderService;
import com.cinema.service.RoleService;
import com.cinema.service.ShoppingCartService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Set;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inject-data")
public class InjectDataController {
    private final RoleService roleService;
    private final AuthenticationService authenticationService;
    private final MovieService movieService;
    private final CinemaHallService cinemaHallService;
    private final MovieSessionService movieSessionService;
    private final ShoppingCartService shoppingCartService;
    private final OrderService orderService;

    public InjectDataController(RoleService roleService,
                                AuthenticationService authenticationService,
                                MovieService movieService,
                                CinemaHallService cinemaHallService,
                                MovieSessionService movieSessionService,
                                ShoppingCartService shoppingCartService,
                                OrderService orderService) {
        this.roleService = roleService;
        this.authenticationService = authenticationService;
        this.movieService = movieService;
        this.cinemaHallService = cinemaHallService;
        this.movieSessionService = movieSessionService;
        this.shoppingCartService = shoppingCartService;
        this.orderService = orderService;
    }

    @GetMapping
    public void injectionData() {
        Role role = new Role(Role.RoleName.USER);
        roleService.add(role);
        Role role2 = new Role(Role.RoleName.ADMIN);
        roleService.add(role2);
        User user = new User("user@com", "111111");
        User admin = new User("admin@com", "111111");
        user = authenticationService.register(user.getEmail(), user.getPassword(),
                Set.of(roleService.getRoleByName("USER")));
        admin = authenticationService.register(admin.getEmail(), admin.getPassword(),
                Set.of(roleService.getRoleByName("ADMIN")));
        Movie movie = new Movie();
        movie.setTitle("Eralash");
        movie.setDescription("Season 1");
        movieService.add(movie);
        CinemaHall cinemaHall = new CinemaHall();
        cinemaHall.setCapacity(31);
        cinemaHall.setDescription("Best cinema hall");
        cinemaHallService.add(cinemaHall);
        MovieSession movieSession = new MovieSession();
        movieSession.setCinemaHall(cinemaHall);
        movieSession.setMovie(movie);
        movieSession.setShowTime(LocalDateTime.of(LocalDate.now(), LocalTime.of(14, 30)));
        movieSessionService.add(movieSession);
        shoppingCartService.addSession(movieSession, user);
        shoppingCartService.addSession(movieSession, admin);
        orderService.completeOrder(shoppingCartService.getByUser(user).getTickets(), user);
    }
}
