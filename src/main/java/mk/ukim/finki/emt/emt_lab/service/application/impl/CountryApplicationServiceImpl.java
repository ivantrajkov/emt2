package mk.ukim.finki.emt.emt_lab.service.application.impl;

import mk.ukim.finki.emt.emt_lab.dto.CreateCountryDto;
import mk.ukim.finki.emt.emt_lab.dto.DisplayCountryDto;
import mk.ukim.finki.emt.emt_lab.model.domain.Country;
import mk.ukim.finki.emt.emt_lab.service.application.CountryApplicationService;
import mk.ukim.finki.emt.emt_lab.service.domain.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryApplicationServiceImpl implements CountryApplicationService {
    private final CountryService countryService;

    public CountryApplicationServiceImpl(CountryService countryService) {
        this.countryService = countryService;
    }

    @Override
    public List<DisplayCountryDto> findAll() {
        return countryService.findAll().stream().map(DisplayCountryDto::from).toList();
    }

    @Override
    public Optional<DisplayCountryDto> findById(Long id) {
        return countryService.findById(id).map(DisplayCountryDto::from);
    }

    @Override
    public Optional<DisplayCountryDto> save(CreateCountryDto dto) {
        return countryService.save(dto.toCountry())
                .map(DisplayCountryDto::from);
    }

    @Override
    public Optional<DisplayCountryDto> update(Long id, CreateCountryDto dto) {
        return countryService.update(id,dto.toCountry())
                .map(DisplayCountryDto::from);
    }

    @Override
    public void deleteById(Long id) {
        countryService.deleteById(id);
    }
}
