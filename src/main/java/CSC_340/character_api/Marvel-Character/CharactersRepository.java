package com.csc340.character_api.characters;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CharacterRepository extends JpaRepository<MarvelCharacter, Long> {

    List<MarvelCharacter> findByUniverseIgnoreCase(String universe);

    List<MarvelCharacter> findByNameContainingIgnoreCase(String name);

    List<MarvelCharacter> findByNameContainingIgnoreCaseAndUniverseIgnoreCase(String name, String universe);
}
