package mk.ukim.finki.emt.emt_lab.service.application;

import mk.ukim.finki.emt.emt_lab.dto.CreateHostDto;
import mk.ukim.finki.emt.emt_lab.dto.DisplayHostDto;

import java.util.List;
import java.util.Optional;

public interface HostApplicationService {
    List<DisplayHostDto> findAll();
    Optional<DisplayHostDto> findById(Long id);
    Optional<DisplayHostDto> save(CreateHostDto dto);
    Optional<DisplayHostDto> update(Long id, CreateHostDto dto);


    void deleteById(Long id);
}
