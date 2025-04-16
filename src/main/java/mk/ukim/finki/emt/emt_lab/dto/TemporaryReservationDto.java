//package mk.ukim.finki.emt.emt_lab.dto;
//
//import mk.ukim.finki.emt.emt_lab.model.domain.TemporaryReservation;
//import mk.ukim.finki.emt.emt_lab.model.domain.Accommodation;
//
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.stream.Collectors;
//
//public record TemporaryReservationDto(
//        Long id,
//        DisplayUserDto user,
//        List<DisplayAccommodationDto> accommodations,
//) {
//
//    public static TemporaryReservationDto from(TemporaryReservation reservation) {
//        return new TemporaryReservationDto(
//                reservation.getId(),
//                DisplayUserDto.from(reservation.getUser()),
//                DisplayAccommodationDto.from(reservation.getAccommodations())
//        );
//    }
//}
