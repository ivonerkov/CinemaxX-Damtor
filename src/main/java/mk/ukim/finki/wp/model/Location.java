package mk.ukim.finki.wp.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @ManyToOne(fetch = FetchType.EAGER)
    private City city;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Movie> movies;

    public Location() {
    }
    public Location(String name, String description, City city, List<Movie> movies) {
        this.name = name;
        this.description = description;
        this.city = city;
        this.movies = movies;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public City getLocation() {
        return city;
    }
    public void setLocation(City city) {
        this.city = city;
    }
    public List<Movie> getMovies() {
        return movies;
    }
    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }
}
