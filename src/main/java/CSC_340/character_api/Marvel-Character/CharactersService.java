package com.csc340.character_api.characters;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CharacterService {

    private final CharacterRepository repository;

    public CharacterService(CharacterRepository repository) {
        this.repository = repository;
    }

    public List<MarvelCharacter> findAll() {
        return repository.findAll();
    }

    public List<MarvelCharacter> search(String name, String universe) {
        boolean hasName = name != null && !name.isBlank();
        boolean hasUniverse = universe != null && !universe.isBlank();

        if (hasName && hasUniverse) {
            return repository.findByNameContainingIgnoreCaseAndUniverseIgnoreCase(name.trim(), universe.trim());
        }
        if (hasName) {
            return repository.findByNameContainingIgnoreCase(name.trim());
        }
        if (hasUniverse) {
            return repository.findByUniverseIgnoreCase(universe.trim());
        }

        return repository.findAll();
    }

    public MarvelCharacter findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new CharacterNotFoundException(id));
    }

    public MarvelCharacter create(MarvelCharacter character) {
        return repository.save(character);
    }

    public MarvelCharacter update(Long id, MarvelCharacter updated) {
        MarvelCharacter existing = findById(id);

        existing.setName(updated.getName());
        existing.setDescription(updated.getDescription());
        existing.setUniverse(updated.getUniverse());
        existing.setTeam(updated.getTeam());

        return repository.save(existing);
    }

    public void delete(Long id) {
        findById(id);
        repository.deleteById(id);
    }
}