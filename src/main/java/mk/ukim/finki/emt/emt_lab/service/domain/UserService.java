package mk.ukim.finki.emt.emt_lab.service.domain;

import mk.ukim.finki.emt.emt_lab.model.domain.User;
import mk.ukim.finki.emt.emt_lab.model.enumerations.Role;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    mk.ukim.finki.emt.emt_lab.model.domain.User register(String username, String password, String repeatPassword, String name, String surname, Role role);

    User login(String username, String password);

    mk.ukim.finki.emt.emt_lab.model.domain.User findByUsername(String username);
}

