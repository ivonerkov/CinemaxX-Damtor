package mk.ukim.finki.wp.service.impl;

import mk.ukim.finki.wp.model.Movie;
import mk.ukim.finki.wp.model.MovieGenre;
import mk.ukim.finki.wp.model.User;
import mk.ukim.finki.wp.model.exceptions.InvalidMovieIdException;
import mk.ukim.finki.wp.model.exceptions.InvalidUserIdException;
import mk.ukim.finki.wp.repository.MovieRepository;
import mk.ukim.finki.wp.repository.UserRepository;
import mk.ukim.finki.wp.service.MovieService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final UserRepository userRepository;

    public MovieServiceImpl(MovieRepository movieRepository, UserRepository userRepository) {
        this.movieRepository = movieRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Movie> listAll() {
        return movieRepository.findAll();
    }

    @Override
    public Movie findById(Long id) {
        return movieRepository.findById(id).orElseThrow(InvalidMovieIdException::new);
    }

    @Override
    public Movie create(String title, String description, MovieGenre movieGenre, List<Long> Users, LocalDate screeningStartDay) {
        return movieRepository.save(new Movie(title, description, movieGenre, userRepository.findAllById(Users), screeningStartDay));
    }

    @Override
    public Movie update(Long id, String title, String description, MovieGenre movieGenre, List<Long> Users) {
        Movie Movie = findById(id);
        Movie.setTitle(title);
        Movie.setDescription(description);
        Movie.setGenre(movieGenre);
        Movie.setUsers(userRepository.findAllById(Users));
        return movieRepository.save(Movie);
    }

    @Override
    public Movie delete(Long id) {
        Movie movie = findById(id);
        movieRepository.delete(movie);
        return movie;
    }

    @Override
    public Movie markLiked(Long id) {
        Movie movie = findById(id);
        movie.setLiked(true);
        return movieRepository.save(movie);
    }

    @Override
    public List<Movie> filter(Long UserId) {
        if(UserId == null)
        {
            return movieRepository.findAll();
        }
        else {
            User user = userRepository.findById(UserId).orElseThrow(InvalidUserIdException::new);
            return movieRepository.findAllByUsersContains(user);
        }
    }
}
