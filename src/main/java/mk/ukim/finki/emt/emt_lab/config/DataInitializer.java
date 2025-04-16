package mk.ukim.finki.emt.emt_lab.config;


import jakarta.annotation.PostConstruct;
import mk.ukim.finki.emt.emt_lab.model.domain.Accommodation;
import mk.ukim.finki.emt.emt_lab.model.domain.Country;
import mk.ukim.finki.emt.emt_lab.model.domain.Host;
import mk.ukim.finki.emt.emt_lab.model.domain.User;
import mk.ukim.finki.emt.emt_lab.model.enumerations.AccommodationCategory;
import mk.ukim.finki.emt.emt_lab.model.enumerations.Role;
import mk.ukim.finki.emt.emt_lab.repository.AccommodationRepository;
import mk.ukim.finki.emt.emt_lab.repository.CountryRepository;
import mk.ukim.finki.emt.emt_lab.repository.HostRepository;
import mk.ukim.finki.emt.emt_lab.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
public class DataInitializer {
    //IS AVAILABLE AT http://localhost:8080/swagger-ui/index.html
    private final AccommodationRepository accommodationRepository;
    private final CountryRepository countryRepository;
    private final HostRepository hostRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(AccommodationRepository accommodationRepository, CountryRepository countryRepository, HostRepository hostRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.accommodationRepository = accommodationRepository;
        this.countryRepository = countryRepository;
        this.hostRepository = hostRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void init() {
        Country macedonia = countryRepository.save(new Country("Macedonia", "Europe"));
        Country germany = countryRepository.save(new Country("Germany", "Europe"));
        Country canada = countryRepository.save(new Country("Canada", "America"));

        Host host1 = hostRepository.save(new Host("Petko", "Petkov", macedonia));
        Host host2 = hostRepository.save(new Host("Marko", "Markov", germany));
        Host host3 = hostRepository.save(new Host("Petre", "Petrov", canada));

        accommodationRepository.save(new Accommodation("Hotel A", AccommodationCategory.HOTEL, host1, 30,false));
        accommodationRepository.save(new Accommodation("Apartment B", AccommodationCategory.APARTMENT, host2, 10,false));
        accommodationRepository.save(new Accommodation("Flat C", AccommodationCategory.FLAT, host3, 5,false));
        userRepository.save(new User(
                "ivan",
                "trajkov",
                "Ivan",
                "Trajkov",
                Role.HOST
        ));

        userRepository.save(new User(
                "user",
                "user",
                "user",
                "user",
                Role.USER
        ));



    }

}