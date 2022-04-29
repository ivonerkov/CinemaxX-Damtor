package mk.ukim.finki.wp.service;

import mk.ukim.finki.wp.model.Movie;
import mk.ukim.finki.wp.model.MovieGenre;


import java.time.LocalDate;
import java.util.List;

public interface MovieService {
    List<Movie> listAll();
    Movie findById(Long id);
    Movie create(String title, String description, MovieGenre movieGenre, List<Long> users, LocalDate dueDate);
    Movie update(Long id, String title, String description, MovieGenre movieGenre, List<Long> users);
    Movie delete(Long id);
    Movie markLiked(Long id);
    List<Movie> filter(Long participantId);
}
