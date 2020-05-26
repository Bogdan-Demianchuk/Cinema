import com.cinema.exeption.AuthenticationException;
import com.cinema.exeption.DataProcessingException;
import com.cinema.lib.Injector;
import com.cinema.model.CinemaHall;
import com.cinema.model.Movie;
import com.cinema.model.MovieSession;
import com.cinema.model.Ticket;
import com.cinema.model.User;
import com.cinema.service.AuthenticationService;
import com.cinema.service.CinemaHallService;
import com.cinema.service.MovieService;
import com.cinema.service.MovieSessionService;
import com.cinema.service.ShoppingCartService;
import com.cinema.service.UserService;
import com.cinema.util.HibernateUtil;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class Main {
    private static Injector injector = Injector.getInstance("com.cinema");

    public static void main(String[] args) throws AuthenticationException {
        checkMovie();
        checkUser();
        checkCinemaHall();
        checkMovieSession();
        checkShoppingCartService();
        checkTicket();
    }

    private static void checkUser() throws AuthenticationException {
        AuthenticationService authenticationService = (AuthenticationService)
                injector.getInstance(AuthenticationService.class);
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
        MovieService movieService = (MovieService) injector.getInstance(MovieService.class);
        movieService.add(movie);
        System.out.println(movieService.getAll());
    }

    private static void checkCinemaHall() {
        CinemaHallService cinemaHallService = (CinemaHallService)
                injector.getInstance(CinemaHallService.class);
        CinemaHall cinemaHall = new CinemaHall();
        cinemaHall.setCapacity(31);
        cinemaHallService.add(cinemaHall);
        System.out.println(cinemaHallService.getAll());
    }

    private static void checkMovieSession() {
        MovieSession movieSession = new MovieSession();
        CinemaHallService cinemaHallService = (CinemaHallService)
                injector.getInstance(CinemaHallService.class);
        movieSession.setCinemaHall(cinemaHallService.getAll().get(0));
        MovieService movieService = (MovieService) injector.getInstance(MovieService.class);
        Movie movie = movieService.getAll().get(0);
        movieSession.setMovie(movie);
        movieSession.setShowTime(LocalDateTime.of(LocalDate.now(), LocalTime.of(14, 30)));
        MovieSessionService movieSessionService = (MovieSessionService)
                injector.getInstance(MovieSessionService.class);
        movieSessionService.add(movieSession);
        System.out.println(movieSessionService.findAvailableSessions(movie.getId(),
                LocalDate.now()));
    }

    private static void checkShoppingCartService() {
        ShoppingCartService shoppingCartService = (ShoppingCartService)
                injector.getInstance(ShoppingCartService.class);
        UserService userService = (UserService)
                injector.getInstance(UserService.class);
        System.out.println(shoppingCartService
                .getByUser(userService.findByEmail("email2@com").get()));
    }

    private static void checkTicket() {
        MovieSession movieSession = new MovieSession();
        CinemaHallService cinemaHallService = (CinemaHallService)
                injector.getInstance(CinemaHallService.class);
        movieSession.setCinemaHall(cinemaHallService.getAll().get(0));
        MovieService movieService = (MovieService) injector.getInstance(MovieService.class);
        Movie movie = movieService.getAll().get(0);
        movieSession.setMovie(movie);
        movieSession.setShowTime(LocalDateTime.of(LocalDate.now(), LocalTime.of(14, 30)));
        MovieSessionService movieSessionService = (MovieSessionService)
                injector.getInstance(MovieSessionService.class);
        movieSessionService.add(movieSession);
        ShoppingCartService shoppingCartService = (ShoppingCartService)
                injector.getInstance(ShoppingCartService.class);
        UserService userService = (UserService)
                injector.getInstance(UserService.class);
        shoppingCartService.addSession(movieSession,
                userService.findByEmail("email2@com").get());
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Ticket> query = session
                    .createQuery("from Ticket", Ticket.class);
            System.out.println(query.list());
        } catch (Exception e) {
            throw new DataProcessingException("Can't get "
                    + "avalibal tickets from db", e);
        }
    }
}
