package com.plb.vinylmgt.web.rest;

import com.plb.vinylmgt.domain.Vinyl;
import com.plb.vinylmgt.service.VinylService;
import org.apache.coyote.Response;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/vinyls")
public class VinylResource {

    private final VinylService vinylService;

    public VinylResource(VinylService vinylService) {
        this.vinylService = vinylService;
    }

    @GetMapping
    public ResponseEntity<List<Vinyl>> getAllVinyls() {
        return ResponseEntity.ok(vinylService.getAllVinyls());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vinyl> getVinyl(@PathVariable Long id) {
        Optional<Vinyl> vinylById = vinylService.getById(id);
        return vinylById.map(ResponseEntity::ok)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Vinyl not found"));
    }

    @PostMapping
    public ResponseEntity<Vinyl> createNewVinyl(@Valid @RequestBody Vinyl newVinyl) {
        return ResponseEntity.ok(vinylService.save(newVinyl));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vinyl> update(@PathVariable Long id, @Valid @RequestBody Vinyl vinylToUpdate) {
        vinylToUpdate.setId(id);
        Vinyl updatedVinyl = vinylService.save(vinylToUpdate);
        return ResponseEntity.ok(updatedVinyl);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Vinyl> delete(@PathVariable Long id) {
        try {
            vinylService.delete(id);
            return ResponseEntity.ok().build();
        } catch (EmptyResultDataAccessException erdae) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Vinyl not found");
        }
    }
}
