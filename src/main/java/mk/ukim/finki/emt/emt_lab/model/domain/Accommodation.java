package mk.ukim.finki.emt.emt_lab.model.domain;


import jakarta.persistence.*;
import lombok.Data;
import mk.ukim.finki.emt.emt_lab.model.enumerations.AccommodationCategory;

@Data
@Entity
public class Accommodation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private AccommodationCategory category;
    @ManyToOne
    private Host host;
    private Integer numRooms;
    private Boolean isTaken = false;

    public Accommodation() {
    }

    public Accommodation(String name, AccommodationCategory category, Host host, Integer numRooms) {
        this.name = name;
        this.category = category;
        this.host = host;
        this.numRooms = numRooms;
        this.isTaken = false;
    }

    public Accommodation(String name, AccommodationCategory category, Host host, Integer numRooms, Boolean isTaken) {
        this.name = name;
        this.category = category;
        this.host = host;
        this.numRooms = numRooms;
        this.isTaken = isTaken;
    }

    public Boolean getTaken() {
        return isTaken;
    }

    public void setTaken(Boolean taken) {
        isTaken = taken;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(AccommodationCategory category) {
        this.category = category;
    }

    public void setHost(Host host) {
        this.host = host;
    }

    public void setNumRooms(Integer numRooms) {
        this.numRooms = numRooms;
    }

    public String getName() {
        return name;
    }

    public AccommodationCategory getCategory() {
        return category;
    }

    public Host getHost() {
        return host;
    }

    public Integer getNumRooms() {
        return numRooms;
    }
}