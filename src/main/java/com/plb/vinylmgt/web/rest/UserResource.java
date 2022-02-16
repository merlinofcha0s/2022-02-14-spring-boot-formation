package com.plb.vinylmgt.web.rest;

import com.plb.vinylmgt.domain.User;
import com.plb.vinylmgt.service.UserService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserResource {

    private final UserService userService;

    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllVinyls() {
        return ResponseEntity.ok(userService.getAllUser());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getVinyl(@PathVariable Long id) {
        Optional<User> vinylById = userService.getById(id);
        return vinylById.map(ResponseEntity::ok).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }

    @PostMapping
    public ResponseEntity<User> createNewVinyl(@Valid @RequestBody User newVinyl) {
        return ResponseEntity.ok(userService.save(newVinyl));
    }

    @PutMapping
    public ResponseEntity<User> updateVinyl(@Valid @RequestBody User updateVinyl) {
        return ResponseEntity.ok(userService.save(updateVinyl));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteVinyl(@PathVariable Long id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.ok().build();
        } catch (EmptyResultDataAccessException erdae) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
    }
}
