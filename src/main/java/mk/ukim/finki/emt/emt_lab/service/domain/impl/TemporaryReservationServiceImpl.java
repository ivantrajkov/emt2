package mk.ukim.finki.emt.emt_lab.service.domain.impl;


import mk.ukim.finki.emt.emt_lab.model.domain.Accommodation;
import mk.ukim.finki.emt.emt_lab.model.domain.TemporaryReservation;
import mk.ukim.finki.emt.emt_lab.model.domain.User;
import mk.ukim.finki.emt.emt_lab.repository.AccommodationRepository;
import mk.ukim.finki.emt.emt_lab.repository.TemporaryReservationRepository;
import mk.ukim.finki.emt.emt_lab.repository.UserRepository;
import mk.ukim.finki.emt.emt_lab.service.domain.AccommodationService;
import mk.ukim.finki.emt.emt_lab.service.domain.TemporaryReservationService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TemporaryReservationServiceImpl implements TemporaryReservationService {

    private final TemporaryReservationRepository temporaryReservationRepository;
    private final AccommodationRepository accommodationRepository;
    private final UserRepository userRepository;
    private final AccommodationService accommodationService;

    public TemporaryReservationServiceImpl(TemporaryReservationRepository temporaryReservationRepository,
                                           AccommodationRepository accommodationRepository,
                                           UserRepository userRepository,
                                           AccommodationService accommodationService) {
        this.temporaryReservationRepository = temporaryReservationRepository;
        this.accommodationRepository = accommodationRepository;
        this.userRepository = userRepository;
        this.accommodationService = accommodationService;
    }

    @Override
    public List<Accommodation> listAllAccommodationsInReservation(Long id) {
        return temporaryReservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reservation not found!"))
                .getAccommodations();
    }

    @Override
    public Optional<TemporaryReservation> getActiveTemporaryReservation(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found!"));
        return temporaryReservationRepository.findByUser(user);
    }

    @Override
    public Optional<TemporaryReservation> addAccommodationToReservation(String username, Long accommodationId) {
        Optional<TemporaryReservation> optionalReservation = getActiveTemporaryReservation(username);

        if (optionalReservation.isPresent()) {
            TemporaryReservation reservation = optionalReservation.get();

            boolean accommodationExists = reservation.getAccommodations().stream()
                    .anyMatch(accommodation -> accommodation.getId().equals(accommodationId));

            if (accommodationExists) {
                throw new RuntimeException("Accommodation already exists in your reservation!");
            }

            Accommodation accommodation = accommodationRepository.findById(accommodationId)
                    .orElseThrow(() -> new RuntimeException("Accommodation not found!"));

            reservation.getAccommodations().add(accommodation);
            temporaryReservationRepository.save(reservation);
            return Optional.of(reservation);
        }

        return Optional.empty();
    }

    @Override
    public void confirmAll(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found!"));
        TemporaryReservation reservation = temporaryReservationRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Reservation not found!"));

        reservation.getAccommodations().forEach(accommodation -> accommodationService.markAsTaken(accommodation.getId()));
        reservation.setAccommodations(new ArrayList<>());
        temporaryReservationRepository.save(reservation);
    }
}
