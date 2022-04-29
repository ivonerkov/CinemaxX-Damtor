package mk.ukim.finki.wp.config;

import mk.ukim.finki.wp.model.City;
import mk.ukim.finki.wp.model.Movie;
import mk.ukim.finki.wp.model.MovieGenre;
import mk.ukim.finki.wp.model.User;
import mk.ukim.finki.wp.service.CityService;
import mk.ukim.finki.wp.service.LocationService;
import mk.ukim.finki.wp.service.MovieService;
import mk.ukim.finki.wp.service.UserService;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
@Component
public class DataInitializer {

    private final UserService userService;
    private final MovieService service;
    private final LocationService locationService;
    private final CityService cityService;

    public DataInitializer(UserService userService, MovieService service, LocationService locationService, CityService cityService) {
        this.userService = userService;
        this.service = service;
        this.locationService = locationService;
        this.cityService = cityService;
    }

    private MovieGenre randomizeGenre(int i) {
        if (i % 3 == 0) return MovieGenre.ACTION;
        else if (i % 3 == 1) return MovieGenre.COMEDY;
        return MovieGenre.ROMANCE;
    }

    @PostConstruct
    public void initData() {
        this.userService.create("ivo_master", "ivo", "ROLE_MASTER");
        for (int i = 1; i < 6; i++) {
            this.userService.create("viewer" + i, "pass" + i, "ROLE_VIEWER");
        }
        List<User> users = this.userService.listAll();
        for (int i = 2; i < 11; i++) {
            this.service.create(
                    "Movie: " + i,
                    "Description." + i,
                    this.randomizeGenre(i),
                    Stream.of(users.get((i - 1) % 5).getId(), users.get((i + 1) % 5).getId()).collect(Collectors.toList()),
                    LocalDate.now().plusDays((i + 11) / 2)
            );
        }
        for (int i = 1; i < 6; i++) {
            if (i % 3 == 0) this.cityService.create("Veles");
            else if (i % 3 == 1) this.cityService.create("Skopje");
            this.cityService.create("Kumanovo");
        }
        for (int i = 1; i < 11; i++) {
            City city = this.cityService.findById(this.cityService.listAll().get((i-1)%5).getId());
            List<Long> m = Stream.of(users.get((i - 1) % 5).getId(), users.get((i + 1) % 5).getId()).collect(Collectors.toList());
            this.locationService.create("Cinema location: " + i, "Description:" + i , city, m);
        }
    }
}
