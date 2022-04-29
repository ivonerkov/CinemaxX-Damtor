package mk.ukim.finki.wp.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate screeningStartDay;
    private String title;
    private String description;
    @Enumerated(EnumType.STRING)
    private MovieGenre movieGenre;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<User> users;
    private Boolean Liked = false;

    public Movie() {
    }

    public Movie(String title, String description, MovieGenre movieGenre, List<User> users, LocalDate screeningStartDay) {
        this.title = title;
        this.description = description;
        this.movieGenre = movieGenre;
        this.users = users;
        this.screeningStartDay = screeningStartDay;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public LocalDate getscreeningStartDay() {
        return screeningStartDay;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public MovieGenre getGenre() {
        return movieGenre;
    }
    public void setGenre(MovieGenre movieGenre) {
        this.movieGenre = movieGenre;
    }
    public List<User> getUsers() {
        return users;
    }
    public void setUsers(List<User> users) {
        this.users = users;
    }
    public Boolean getLiked() {
        return Liked;
    }
    public void setLiked(Boolean Liked) {
        this.Liked = Liked;
    }

}
