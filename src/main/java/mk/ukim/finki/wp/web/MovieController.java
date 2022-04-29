package mk.ukim.finki.wp.web;

import mk.ukim.finki.wp.model.City;
import mk.ukim.finki.wp.model.Location;
import mk.ukim.finki.wp.model.Movie;
import mk.ukim.finki.wp.model.MovieGenre;
import mk.ukim.finki.wp.service.CityService;
import mk.ukim.finki.wp.service.LocationService;
import mk.ukim.finki.wp.service.MovieService;
import mk.ukim.finki.wp.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("")
public class MovieController {

    private final MovieService service;
    private final LocationService locationService;
    private final UserService userService;
    private final CityService cityService;

    public MovieController(MovieService service, UserService userService, LocationService locationService, CityService cityService) {
        this.service = service;
        this.userService = userService;
        this.locationService = locationService;
        this.cityService = cityService;
    }

    @GetMapping({"/","/Movies"})
    public String showList(@RequestParam(required = false) Long UserId,
                           Model model) {
        List<Movie> Movies;
        if (UserId == null) {
            Movies = this.service.listAll();
        } else {
            Movies = this.service.filter(UserId);
        }
        model.addAttribute("Movies", Movies);
        model.addAttribute("users", userService.listAll());
        return "list";
    }

    @GetMapping("/Movies/add")
    public String showAddM(Model model) {
        model.addAttribute("Genres",MovieGenre.values());
        model.addAttribute("users",userService.listAll());
        return "form";
    }

    @GetMapping("/Movies/{id}/edit")
    public String showEditM(@PathVariable Long id,
                            Model model) {
        model.addAttribute("Movie",service.findById(id));
        model.addAttribute("users",userService.listAll());
        model.addAttribute("Genres",MovieGenre.values());
        return "form";
    }

    @PostMapping("/Movies")
    public String createM(@RequestParam String title,
                          @RequestParam String description,
                          @RequestParam MovieGenre MovieGenre,
                          @RequestParam List<Long> Users,
                          @RequestParam String screeningStartDay) {
        this.service.create(title, description, MovieGenre, Users, LocalDate.parse(screeningStartDay));
        return "redirect:/Movies";
    }

    @PostMapping("/Movies/{id}")
    public String update(@PathVariable Long id,
                         @RequestParam String title,
                         @RequestParam String description,
                         @RequestParam MovieGenre MovieGenre,
                         @RequestParam List<Long> Users) {
        this.service.update(id, title, description, MovieGenre, Users);
        return "redirect:/Movies";
    }

    @PostMapping("/Movies/{id}/delete")
    public String deleteM(@PathVariable Long id) {
        this.service.delete(id);
        return "redirect:/Movies";
    }

    @PostMapping("/Movies/{id}/mark_liked")
    public String markLiked(@PathVariable Long id) {
        this.service.markLiked(id);
        return "redirect:/Movies";
    }

    @GetMapping("/locations")
    public String showLocations(Model model) {
        List<Location> locations;
        locations = this.locationService.listAllLocations();

        model.addAttribute("locations", locations);
        model.addAttribute("cities", this.cityService.listAll());
        model.addAttribute("movies", this.service.listAll());
        return "list1";
    }

    @GetMapping("/locations/add")
    public String showAdd(Model model) {
        model.addAttribute("cities", this.cityService.listAll());
        model.addAttribute("movies", this.service.listAll());
        return "form1";
    }

    @GetMapping("/locations/{id}/edit")
    public String showEdit(@PathVariable Long id,
                           Model model) {
        model.addAttribute("location", this.locationService.findById(id));
        model.addAttribute("cities", this.cityService.listAll());
        model.addAttribute("movies", this.service.listAll());
        return "form1";
    }

    @PostMapping("/locations")
    public String create(@RequestParam String name,
                         @RequestParam String description,
                         @RequestParam City city,
                         @RequestParam List<Long> movies) {
        this.locationService.create(name, description, city, movies);
        return "redirect:/locations";
    }

    @PostMapping("/locations/{id}")
    public String update(@PathVariable Long id,
                         @RequestParam String name,
                         @RequestParam String description,
                         @RequestParam Long location,
                         @RequestParam List<Long> movies) {
        this.locationService.update(id, name, description, location, movies);
        return "redirect:/locations";
    }

    @PostMapping("/locations/{id}/delete")
    public String delete(@PathVariable Long id) {
        this.locationService.delete(id);
        return "redirect:/locations";
    }
}