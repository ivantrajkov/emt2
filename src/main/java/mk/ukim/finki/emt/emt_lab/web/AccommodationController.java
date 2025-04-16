package mk.ukim.finki.emt.emt_lab.web;

import io.swagger.v3.oas.annotations.Operation;
import mk.ukim.finki.emt.emt_lab.dto.CreateAccommodationDto;
import mk.ukim.finki.emt.emt_lab.dto.DisplayAccommodationDto;
import mk.ukim.finki.emt.emt_lab.exceptions.AccommodationNotFound;
import mk.ukim.finki.emt.emt_lab.service.application.AccommodationApplicationService;
import mk.ukim.finki.emt.emt_lab.service.domain.AccommodationService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/api/accommodations", "/"})

public class AccommodationController {
    private final AccommodationApplicationService accommodationApplicationService;

    public AccommodationController(AccommodationApplicationService accommodationApplicationService) {
        this.accommodationApplicationService = accommodationApplicationService;
    }

    @Operation(summary = "Get all accommodations", description = "Returns a list of all available accommodations.")
    @GetMapping
    public List<DisplayAccommodationDto> findAll(){
        return accommodationApplicationService.findAll();
    }

    @Operation(summary = "Get accommodation by ID", description = "Returns the accommodation with the specified ID.")
    @GetMapping("/{id}")
    public ResponseEntity<DisplayAccommodationDto> findById(@PathVariable Long id){
        return accommodationApplicationService.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Create a new accommodation", description = "Adds a new accommodation and returns the created resource.")
    @PostMapping("/add")
    public ResponseEntity<DisplayAccommodationDto> save(@RequestBody CreateAccommodationDto createAccommodationDto){
        return accommodationApplicationService.save(createAccommodationDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Update accommodation", description = "Updates the accommodation with the given ID and returns the updated data.")
    @PutMapping("/edit/{id}")
    public ResponseEntity<DisplayAccommodationDto> update(@PathVariable Long id, @RequestBody CreateAccommodationDto accommodationDto){
        return accommodationApplicationService.update(id,accommodationDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Delete accommodation", description = "Deletes the accommodation with the given ID if it exists.")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<DisplayAccommodationDto> delete(@PathVariable Long id){
        if(accommodationApplicationService.findById(id).isPresent()){
            accommodationApplicationService.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Rent an accommodation", description = "Rents the accommodation with the given ID if it exists.")
    @PutMapping("/markAsRented/{id}")
    public ResponseEntity<Void> markAsRented(@PathVariable Long id){
        try {
            accommodationApplicationService.markAsTaken(id);
            return ResponseEntity.noContent().build();
        } catch (AccommodationNotFound ex){
            return ResponseEntity.notFound().build();
        }
    }

}
