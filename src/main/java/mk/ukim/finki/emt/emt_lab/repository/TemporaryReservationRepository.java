package mk.ukim.finki.emt.emt_lab.repository;

import mk.ukim.finki.emt.emt_lab.model.domain.TemporaryReservation;
import mk.ukim.finki.emt.emt_lab.model.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TemporaryReservationRepository extends JpaRepository<TemporaryReservation,Long> {
    Optional<TemporaryReservation> findByUser(User user);
}
