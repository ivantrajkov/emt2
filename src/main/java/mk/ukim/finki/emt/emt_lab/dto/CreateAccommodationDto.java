package mk.ukim.finki.emt.emt_lab.dto;

import mk.ukim.finki.emt.emt_lab.model.domain.Accommodation;
import mk.ukim.finki.emt.emt_lab.model.domain.Host;
import mk.ukim.finki.emt.emt_lab.model.enumerations.AccommodationCategory;

import java.util.List;
import java.util.stream.Collectors;

public record CreateAccommodationDto(
        String name,
        AccommodationCategory category,
        Long host,
        Integer numRooms,
        Boolean isTaken
) {
    public static CreateAccommodationDto from(Accommodation accommodation){
        return  new CreateAccommodationDto(
                accommodation.getName(),
                accommodation.getCategory(),
                accommodation.getHost().getId(),
                accommodation.getNumRooms(),
                accommodation.getTaken()
        );
    }

    public static List<CreateAccommodationDto> from(List<Accommodation> accommodations){
        return accommodations.stream().map(CreateAccommodationDto::from).collect(Collectors.toList());
    }

    public Accommodation toAccommodation(Host host){
        return new Accommodation(name,category,host,numRooms,isTaken);
    }

}
