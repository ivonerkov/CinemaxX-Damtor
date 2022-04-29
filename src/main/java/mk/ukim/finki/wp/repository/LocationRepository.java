package mk.ukim.finki.wp.repository;

import mk.ukim.finki.wp.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<Location,Long> {
}
