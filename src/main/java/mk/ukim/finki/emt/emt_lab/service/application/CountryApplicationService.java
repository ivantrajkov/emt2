package mk.ukim.finki.emt.emt_lab.service.application;

import mk.ukim.finki.emt.emt_lab.dto.CreateCountryDto;
import mk.ukim.finki.emt.emt_lab.dto.DisplayCountryDto;

import java.util.List;
import java.util.Optional;

public interface CountryApplicationService {
    List<DisplayCountryDto> findAll();
    Optional<DisplayCountryDto> findById(Long id);
    Optional<DisplayCountryDto> save(CreateCountryDto dto);
    Optional<DisplayCountryDto> update(Long id, CreateCountryDto dto);
    void deleteById(Long id);
}
