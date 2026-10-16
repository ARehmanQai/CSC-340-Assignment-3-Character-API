package com.csc340.character_api.characters;

import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/characters")
public class CharacterController {

    private final CharacterService service;

    public CharacterController(CharacterService service) {
        this.service = service;
    }

    @GetMapping
    public List<MarvelCharacter> list(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String universe) {
        return service.search(name, universe);
    }

    @GetMapping("/{id}")
    public MarvelCharacter get(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MarvelCharacter create(
            @Valid @RequestBody MarvelCharacter character) {
        return service.create(character);
    }

    @PutMapping("/{id}")
    public MarvelCharacter update(
            @PathVariable Long id,
            @Valid @RequestBody MarvelCharacter character) {
        return service.update(id, character);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    // Pulse check endpoint
    @GetMapping("/pulse")
    public String checkPulse() {
        return "Pulse check successful";
    }
}