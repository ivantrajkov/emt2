package mk.ukim.finki.emt.emt_lab.service.application;

import mk.ukim.finki.emt.emt_lab.dto.CreateAccommodationDto;
import mk.ukim.finki.emt.emt_lab.dto.DisplayAccommodationDto;

import java.util.List;
import java.util.Optional;

public interface AccommodationApplicationService {
    List<DisplayAccommodationDto> findAll();
    Optional<DisplayAccommodationDto> findById(Long id);
    Optional<DisplayAccommodationDto> update(Long id, CreateAccommodationDto dto);
    Optional<DisplayAccommodationDto> save(CreateAccommodationDto dto);
    void deleteById(Long id);
    void markAsTaken(Long id);
}
