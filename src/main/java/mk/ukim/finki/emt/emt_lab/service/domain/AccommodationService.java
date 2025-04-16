package mk.ukim.finki.emt.emt_lab.service.domain;

import mk.ukim.finki.emt.emt_lab.model.domain.Accommodation;

import java.util.List;
import java.util.Optional;

public interface AccommodationService {
    List<Accommodation> findAll();
    Optional<Accommodation> findById(Long id);
    Optional<Accommodation> update(Long id, Accommodation accommodation);
    Optional<Accommodation> save(Accommodation accommodation);
    void deleteById(Long id);
    void markAsTaken(Long id);
}
