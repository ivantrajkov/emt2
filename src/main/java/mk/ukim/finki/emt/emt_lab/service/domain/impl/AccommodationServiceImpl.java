package mk.ukim.finki.emt.emt_lab.service.domain.impl;

import mk.ukim.finki.emt.emt_lab.exceptions.AccommodationNotFound;
import mk.ukim.finki.emt.emt_lab.model.domain.Accommodation;
import mk.ukim.finki.emt.emt_lab.repository.AccommodationRepository;
import mk.ukim.finki.emt.emt_lab.service.domain.AccommodationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccommodationServiceImpl implements AccommodationService {
    private final AccommodationRepository accommodationRepository;

    public AccommodationServiceImpl(AccommodationRepository accommodationRepository) {
        this.accommodationRepository = accommodationRepository;
    }

    @Override
    public List<Accommodation> findAll() {
        return accommodationRepository.findAll();
    }

    @Override
    public Optional<Accommodation> findById(Long id) {
        return accommodationRepository.findById(id);
    }

    @Override
    public Optional<Accommodation> update(Long id, Accommodation accommodation) {
        return accommodationRepository.findById(id).map(existingAccommodation -> {
            if(accommodation.getName() != null){
                existingAccommodation.setName(accommodation.getName());
            }
            if(accommodation.getNumRooms() != null){
                existingAccommodation.setNumRooms(accommodation.getNumRooms());
            }
            if(accommodation.getCategory() != null){
                existingAccommodation.setCategory(accommodation.getCategory());
            }
            if(accommodation.getHost() != null){
                existingAccommodation.setHost(accommodation.getHost());
            }
            if(accommodation.getTaken() != null){
                existingAccommodation.setTaken(accommodation.getTaken());
            }
            return accommodationRepository.save(existingAccommodation);
        });
    }

    @Override
    public Optional<Accommodation> save(Accommodation accommodation) {
        return Optional.of(accommodationRepository.save(accommodation));
    }

    @Override
    public void deleteById(Long id) {
        accommodationRepository.deleteById(id);

    }

    @Override
    public void markAsTaken(Long id) {
        //todo: smeni so boolean
        Accommodation accommodation = this.findById(id).orElseThrow(() -> new AccommodationNotFound(id));
        accommodation.setTaken(!accommodation.getTaken());
        accommodationRepository.save(accommodation);
    }
}
