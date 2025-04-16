package mk.ukim.finki.emt.emt_lab.web;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import mk.ukim.finki.emt.emt_lab.model.domain.Accommodation;
import mk.ukim.finki.emt.emt_lab.model.domain.TemporaryReservation;
import mk.ukim.finki.emt.emt_lab.service.domain.AccommodationService;
import mk.ukim.finki.emt.emt_lab.service.domain.TemporaryReservationService;
import mk.ukim.finki.emt.emt_lab.service.domain.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reservations")
@Tag(name = "Temporary Reservations API", description = "Endpoints for managing temporary reservations of accommodations")
public class TemporaryReservationController {

    private final TemporaryReservationService temporaryReservationService;

    public TemporaryReservationController(TemporaryReservationService temporaryReservationService) {
        this.temporaryReservationService = temporaryReservationService;
    }

    @Operation(summary = "Add accommodation to temporary reservation list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Accommodation added to reservation list"),
            @ApiResponse(responseCode = "400", description = "Accommodation already reserved or unavailable")
    })
    @PostMapping("/{username}/add/{accommodationId}")
    public ResponseEntity<String> addAccommodationToReservation(
            @PathVariable String username,
            @PathVariable Long accommodationId) {

        Optional<TemporaryReservation> reservation =
                temporaryReservationService.addAccommodationToReservation(username, accommodationId);

        if (reservation.isPresent()) {
            return ResponseEntity.ok("Accommodation added to your temporary reservation list.");
        } else {
            return ResponseEntity.badRequest().body("Accommodation could not be added. It might be unavailable or already reserved.");
        }
    }

    @Operation(summary = "Get all accommodations in temporary reservation list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Temporary reservation list retrieved"),
            @ApiResponse(responseCode = "404", description = "No active reservation list found")
    })
    @GetMapping("/{username}")
    public ResponseEntity<List<Accommodation>> getTemporaryReservation(@PathVariable String username) {
        return temporaryReservationService.getActiveTemporaryReservation(username)
                .map(reservation -> ResponseEntity.ok(reservation.getAccommodations()))
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Confirm and finalize all accommodations in the temporary reservation list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All accommodations reserved successfully"),
            @ApiResponse(responseCode = "400", description = "Failed to confirm reservations")
    })
    @PostMapping("/{username}/confirm")
    public ResponseEntity<String> confirmReservation(@PathVariable String username) {
        try {
            temporaryReservationService.confirmAll(username);
            return ResponseEntity.ok("All accommodations in your temporary reservation list have been successfully reserved.");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Failed to confirm reservation: " + e.getMessage());
        }
    }
}