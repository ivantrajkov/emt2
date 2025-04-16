package mk.ukim.finki.emt.emt_lab.service.application;

import mk.ukim.finki.emt.emt_lab.dto.CreateUserDto;
import mk.ukim.finki.emt.emt_lab.dto.DisplayUserDto;
import mk.ukim.finki.emt.emt_lab.dto.LoginUserDto;

import java.util.Optional;

public interface UserApplicationService {
    Optional<DisplayUserDto> register(CreateUserDto createUserDto);

    Optional<DisplayUserDto> login(LoginUserDto loginUserDto);

    Optional<DisplayUserDto> findByUsername(String username);

}
