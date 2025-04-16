package mk.ukim.finki.emt.emt_lab.model.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class TemporaryReservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private User user;

    @OneToMany
    private List<Accommodation> accommodations;

    public TemporaryReservation() {
    }
    public TemporaryReservation(User user) {
        this.user = user;
        this.accommodations = new ArrayList<>();
    }

    public List<Accommodation> getAccommodations() {
        return accommodations;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setAccommodations(List<Accommodation> accommodations) {
        this.accommodations = accommodations;
    }
}
