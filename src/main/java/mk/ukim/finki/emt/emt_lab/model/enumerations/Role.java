package mk.ukim.finki.emt.emt_lab.model.enumerations;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER, HOST;

    @Override
    public String getAuthority() {
        return name();
    }
}
