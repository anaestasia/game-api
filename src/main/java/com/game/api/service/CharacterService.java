package com.game.api.service;

import com.game.api.entity.Character;
import com.game.api.repository.CharacterRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CharacterService {

    @Autowired
    private CharacterRepository characterRepository;

    public List<Character> getAllCharacters() {
        return characterRepository.findAll();
    }

    public Character getCharacterById(Long id) {
        Optional<Character> optionalCharacter = characterRepository.findById(id);
        if(optionalCharacter.isPresent()) {
            return optionalCharacter.get();
        } else {
            throw new EntityNotFoundException("Character not found with id: "+ id);
        }
    }

    public Character createCharacter(Character character) {
        return characterRepository.save(character);
    }

    public Character updateCharacter(Character character) {
        // TODO : validation
        return characterRepository.save(character);
    }

    public void deleteCharacter(Long id) {
        if(characterRepository.existsById(id)) {
            characterRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Character not found with id : "+ id);
        }
    }
}
