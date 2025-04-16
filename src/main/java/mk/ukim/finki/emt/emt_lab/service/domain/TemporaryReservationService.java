package mk.ukim.finki.emt.emt_lab.service.domain;

import mk.ukim.finki.emt.emt_lab.model.domain.Accommodation;
import mk.ukim.finki.emt.emt_lab.model.domain.TemporaryReservation;

import java.util.List;
import java.util.Optional;


public interface TemporaryReservationService {

    List<Accommodation> listAllAccommodationsInReservation(Long id);

    Optional<TemporaryReservation> getActiveTemporaryReservation(String username);

    Optional<TemporaryReservation> addAccommodationToReservation(String username, Long accommodationId);

    void confirmAll(String username);
}