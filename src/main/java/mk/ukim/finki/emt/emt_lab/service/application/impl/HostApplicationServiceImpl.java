package mk.ukim.finki.emt.emt_lab.service.application.impl;

import mk.ukim.finki.emt.emt_lab.dto.CreateHostDto;
import mk.ukim.finki.emt.emt_lab.dto.DisplayHostDto;
import mk.ukim.finki.emt.emt_lab.model.domain.Country;
import mk.ukim.finki.emt.emt_lab.service.application.HostApplicationService;
import mk.ukim.finki.emt.emt_lab.service.domain.CountryService;
import mk.ukim.finki.emt.emt_lab.service.domain.HostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HostApplicationServiceImpl implements HostApplicationService {
    private final HostService hostService;
    private final CountryService countryService;

    public HostApplicationServiceImpl(HostService hostService, CountryService countryService) {
        this.hostService = hostService;
        this.countryService = countryService;
    }

    @Override
    public List<DisplayHostDto> findAll() {
        return hostService.findAll().stream().map(DisplayHostDto::from).toList();
    }

    @Override
    public Optional<DisplayHostDto> findById(Long id) {
        return hostService.findById(id).map(DisplayHostDto::from);
    }

    @Override
    public void deleteById(Long id) {
        hostService.deleteById(id);
    }

    @Override
    public Optional<DisplayHostDto> update(Long id, CreateHostDto dto) {
        Optional<Country> country = countryService.findById(dto.country());
        return hostService.update(id,dto.toHost(country.orElse(null))).map(DisplayHostDto::from);
    }

    @Override
    public Optional<DisplayHostDto> save(CreateHostDto dto) {
        Optional<Country> country = countryService.findById(dto.country());
        return hostService.save(dto.toHost(country.orElse(null))).map(DisplayHostDto::from);
    }
}
