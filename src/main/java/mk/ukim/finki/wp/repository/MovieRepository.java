package mk.ukim.finki.wp.repository;

import mk.ukim.finki.wp.model.Movie;
import mk.ukim.finki.wp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie,Long> {
    List<Movie> findAllByUsersContains(User user);
}
