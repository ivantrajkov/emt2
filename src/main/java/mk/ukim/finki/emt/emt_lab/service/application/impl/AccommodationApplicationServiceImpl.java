package mk.ukim.finki.emt.emt_lab.service.application.impl;

import mk.ukim.finki.emt.emt_lab.dto.CreateAccommodationDto;
import mk.ukim.finki.emt.emt_lab.dto.DisplayAccommodationDto;
import mk.ukim.finki.emt.emt_lab.model.domain.Host;
import mk.ukim.finki.emt.emt_lab.service.application.AccommodationApplicationService;
import mk.ukim.finki.emt.emt_lab.service.domain.AccommodationService;
import mk.ukim.finki.emt.emt_lab.service.domain.HostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccommodationApplicationServiceImpl implements AccommodationApplicationService {
    private final AccommodationService accommodationService;
    private final HostService hostService;
    public AccommodationApplicationServiceImpl(AccommodationService accommodationService, HostService hostService) {
        this.accommodationService = accommodationService;
        this.hostService = hostService;
    }

    @Override
    public List<DisplayAccommodationDto> findAll() {
        return accommodationService.findAll().stream().map(DisplayAccommodationDto::from).toList();
    }

    @Override
    public Optional<DisplayAccommodationDto> findById(Long id) {
        return accommodationService.findById(id).map(DisplayAccommodationDto::from);
    }

    @Override
    public Optional<DisplayAccommodationDto> update(Long id, CreateAccommodationDto dto) {
        Optional<Host> host = hostService.findById(dto.host());
        return accommodationService.update(id, dto.toAccommodation(host.orElse(null)))
                .map(DisplayAccommodationDto::from);
    }


    @Override
    public Optional<DisplayAccommodationDto> save(CreateAccommodationDto dto) {
        Optional<Host> host = hostService.findById(dto.host());
        return accommodationService.save(dto.toAccommodation(host.orElse(null)))
                .map(DisplayAccommodationDto::from);
    }

    @Override
    public void deleteById(Long id) {
        accommodationService.deleteById(id);
    }

    @Override
    public void markAsTaken(Long id) {
        accommodationService.markAsTaken(id);
    }
}
