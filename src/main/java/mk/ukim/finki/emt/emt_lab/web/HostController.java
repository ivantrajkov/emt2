package mk.ukim.finki.emt.emt_lab.web;

import io.swagger.v3.oas.annotations.Operation;
import mk.ukim.finki.emt.emt_lab.dto.CreateHostDto;
import mk.ukim.finki.emt.emt_lab.dto.DisplayHostDto;
import mk.ukim.finki.emt.emt_lab.service.application.HostApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/host")
public class HostController {
    private final HostApplicationService hostApplicationService;

    public HostController(HostApplicationService hostApplicationService) {
        this.hostApplicationService = hostApplicationService;
    }

    @Operation(summary = "Get all hosts", description = "Returns a list of all hosts in the system.")
    @GetMapping
    public List<DisplayHostDto> findAll(){
        return hostApplicationService.findAll();
    }

    @Operation(summary = "Get host by ID", description = "Returns a host based on its unique ID.")
    @GetMapping("/{id}")
    public ResponseEntity<DisplayHostDto> findById(@PathVariable Long id){
        return hostApplicationService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Create a new host", description = "Creates a new host and returns the created host data.")
    @PostMapping("/add")
    public ResponseEntity<DisplayHostDto> save(@RequestBody CreateHostDto createHostDto){
        return hostApplicationService.save(createHostDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Update an existing host", description = "Updates a host based on ID and returns the updated data.")
    @PutMapping("/edit/{id}")
    public ResponseEntity<DisplayHostDto> update(@PathVariable Long id, @RequestBody CreateHostDto createHostDto){
        return hostApplicationService.update(id,createHostDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Delete host by ID", description = "Deletes a host based on ID if it exists.")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<DisplayHostDto> delete(@PathVariable Long id){
        if(hostApplicationService.findById(id).isPresent()){
            hostApplicationService.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

}
