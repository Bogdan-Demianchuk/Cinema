import com.cinema.exeption.AuthenticationException;
import com.cinema.exeption.DataProcessingException;
import com.cinema.model.CinemaHall;
import com.cinema.model.Movie;
import com.cinema.model.MovieSession;
import com.cinema.model.Order;
import com.cinema.model.Ticket;
import com.cinema.model.User;
import com.cinema.service.AuthenticationService;
import com.cinema.service.CinemaHallService;
import com.cinema.service.MovieService;
import com.cinema.service.MovieSessionService;
import com.cinema.service.OrderService;
import com.cinema.service.ShoppingCartService;
import com.cinema.service.UserService;
import config.AppConfig;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class Main {
    static AnnotationConfigApplicationContext context =
            new AnnotationConfigApplicationContext(AppConfig.class);

    public static void main(String[] args) throws AuthenticationException {
        checkMovie();
        checkUser();
        checkCinemaHall();
        checkMovieSession();
        checkShoppingCartService();
        checkTicket();
        checkOrder();
    }

    private static void checkUser() throws AuthenticationException {
        AuthenticationService authenticationService = context.getBean(AuthenticationService.class);
        for (int i = 0; i < 10; i++) {
            User user = new User("email" + i + "@com", "*" + i);
            authenticationService.register(user.getEmail(), user.getPassword());
        }
        System.out.println("Get 3rd user -" + authenticationService.login("email2@com", "*2"));
    }

    private static void checkMovie() {
        Movie movie = new Movie();
        movie.setTitle("Eralash");
        movie.setDescription("Season 1");
        MovieService movieService = context.getBean(MovieService.class);
        movieService.add(movie);
        System.out.println(movieService.getAll());
    }

    private static void checkCinemaHall() {
        CinemaHallService cinemaHallService = context.getBean(CinemaHallService.class);
        CinemaHall cinemaHall = new CinemaHall();
        cinemaHall.setCapacity(31);
        cinemaHallService.add(cinemaHall);
        System.out.println(cinemaHallService.getAll());
    }

    private static void checkMovieSession() {
        MovieSession movieSession = new MovieSession();
        CinemaHallService cinemaHallService = context.getBean(CinemaHallService.class);
        movieSession.setCinemaHall(cinemaHallService.getAll().get(0));
        MovieService movieService = context.getBean(MovieService.class);
        Movie movie = movieService.getAll().get(0);
        movieSession.setMovie(movie);
        movieSession.setShowTime(LocalDateTime.of(LocalDate.now(), LocalTime.of(14, 30)));
        MovieSessionService movieSessionService = context.getBean(MovieSessionService.class);
        movieSessionService.add(movieSession);
        System.out.println(movieSessionService.findAvailableSessions(movie.getId(),
                LocalDate.now()));
    }

    private static void checkShoppingCartService() {
        ShoppingCartService shoppingCartService = context.getBean(ShoppingCartService.class);
        UserService userService = context.getBean(UserService.class);
        System.out.println(shoppingCartService
                .getByUser(userService.findByEmail("email2@com").get()));
    }

    private static void checkTicket() {
        MovieSession movieSession = new MovieSession();
        CinemaHallService cinemaHallService = context.getBean(CinemaHallService.class);
        movieSession.setCinemaHall(cinemaHallService.getAll().get(0));
        MovieService movieService = context.getBean(MovieService.class);
        Movie movie = movieService.getAll().get(0);
        movieSession.setMovie(movie);
        movieSession.setShowTime(LocalDateTime.of(LocalDate.now(), LocalTime.of(14, 30)));
        MovieSessionService movieSessionService = context.getBean(MovieSessionService.class);
        movieSessionService.add(movieSession);
        ShoppingCartService shoppingCartService = context.getBean(ShoppingCartService.class);
        UserService userService = context.getBean(UserService.class);
        shoppingCartService.addSession(movieSession,
                userService.findByEmail("email2@com").get());
        try (Session session = context.getBean(SessionFactory.class).openSession()) {
            Query<Ticket> query = session
                    .createQuery("from Ticket", Ticket.class);
            System.out.println(query.list());
        } catch (Exception e) {
            throw new DataProcessingException("Can't get "
                    + "avalibal tickets from db", e);
        }
    }

    private static void checkOrder() {
        UserService userService = context.getBean(UserService.class);
        ShoppingCartService shoppingCartService = context.getBean(ShoppingCartService.class);
        OrderService orderService = context.getBean(OrderService.class);
        orderService.completeOrder(shoppingCartService
                        .getByUser(userService.findByEmail("email2@com").get()).getTickets(),
                userService.findByEmail("email2@com").get());
        List<Order> orderHistory = orderService
                .getOrderHistory(userService.findByEmail("email2@com").get());
        System.out.println(orderHistory);
    }
}
