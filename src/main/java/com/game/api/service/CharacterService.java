package com.game.api.service;

import com.game.api.dto.request.CreateCharacterDTO;
import com.game.api.dto.request.UpdateCharacterDTO;
import com.game.api.dto.response.CharacterResponseDTO;
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

    public Character createCharacter(CreateCharacterDTO requestDTO) {
        Character createdCharacter = Character.builder()
                .name(requestDTO.getName())
                .build();
        return characterRepository.save(createdCharacter);
    }

    public CharacterResponseDTO updateCharacter(Long id, UpdateCharacterDTO requestDTO) {

        // Get the character if exists
        Character updatedCharacter = characterRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Character not found with id : " + id));

        // Update fields
        updatedCharacter.setName(requestDTO.getName());
        updatedCharacter.setPv(requestDTO.getPv());
        updatedCharacter.setAtq(requestDTO.getAtq());
        updatedCharacter.setDef(requestDTO.getDef());
        updatedCharacter.setSpeed(requestDTO.getSpeed());
        updatedCharacter.setSlots(requestDTO.getSlots());

        // Save in DB
        characterRepository.save(updatedCharacter);

        // Return ResponseDTO
        return CharacterResponseDTO.builder()
                .id(updatedCharacter.getId())
                .name(updatedCharacter.getName())
                .pv(updatedCharacter.getPv())
                .atq(updatedCharacter.getAtq())
                .def(updatedCharacter.getDef())
                .speed(updatedCharacter.getSpeed())
                .slots(updatedCharacter.getSlots())
                .build();
    }

    public void deleteCharacter(Long id) {
        if(characterRepository.existsById(id)) {
            characterRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Character not found with id : "+ id);
        }
    }
}
