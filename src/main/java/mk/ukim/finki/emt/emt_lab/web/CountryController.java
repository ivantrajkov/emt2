package mk.ukim.finki.emt.emt_lab.web;

import io.swagger.v3.oas.annotations.Operation;
import mk.ukim.finki.emt.emt_lab.dto.CreateCountryDto;
import mk.ukim.finki.emt.emt_lab.dto.DisplayCountryDto;
import mk.ukim.finki.emt.emt_lab.service.application.CountryApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/country")
public class CountryController {
    private final CountryApplicationService countryApplicationService;

    public CountryController(CountryApplicationService countryApplicationService) {
        this.countryApplicationService = countryApplicationService;
    }

    @Operation(summary = "Get all countries", description = "Returns a list of all countries.")
    @GetMapping
    public List<DisplayCountryDto> findAll(){
        return countryApplicationService.findAll();
    }

    @Operation(summary = "Get country by ID", description = "Returns the country with the specified ID.")
    @GetMapping("/{id}")
    public ResponseEntity<DisplayCountryDto> findById(@PathVariable Long id){
        return countryApplicationService.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }


    @Operation(summary = "Add a new country", description = "Creates a new country with the given data.")
    @PostMapping("/add")
    public ResponseEntity<DisplayCountryDto> save(@RequestBody CreateCountryDto countryDto){
        return countryApplicationService.save(countryDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Edit a country", description = "Updates an existing country by ID with the provided information.")
    @PutMapping("/edit/{id}")
    public ResponseEntity<DisplayCountryDto> update(@PathVariable Long id, @RequestBody CreateCountryDto countryDto){
        return countryApplicationService.update(id,countryDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Delete a country", description = "Deletes a country by its ID if it exists.")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<DisplayCountryDto> delete(@PathVariable Long id){
        if(countryApplicationService.findById(id).isPresent()){
            countryApplicationService.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
