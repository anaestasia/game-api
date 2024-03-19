package com.game.api.service;

import com.game.api.dto.request.CreateCharacterRequestDTO;
import com.game.api.dto.request.UpdateCharacterRequestDTO;
import com.game.api.dto.response.CharacterResponseDTO;
import com.game.api.entity.Character;
import com.game.api.exception.NameAlreadyTakenException;
import com.game.api.mapper.CharacterMapper;
import com.game.api.repository.CharacterRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CharacterService {

    private final CharacterRepository characterRepository;

    @Autowired
    private CharacterMapper characterMapper;

    public List<CharacterResponseDTO> getAllCharacters() {
        List<Character> characters = characterRepository.findAll();
        return characters.stream()
                .map(characterMapper::characterToCharacterResponseDTO)
                .collect(Collectors.toList());
    }

    public CharacterResponseDTO getCharacterById(Long id) {
        Optional<Character> optionalCharacter = characterRepository.findById(id);
        if(optionalCharacter.isPresent()) {
            return characterMapper.characterToCharacterResponseDTO(optionalCharacter.get());
        } else {
            throw new EntityNotFoundException("Character not found with id: "+ id);
        }
    }

    public CharacterResponseDTO createCharacter(CreateCharacterRequestDTO requestDTO) {

        Optional<Character> existingCharacter = characterRepository.findByName(requestDTO.getName());
        if (existingCharacter.isPresent()) {
            throw new NameAlreadyTakenException("Le nom est déjà pris. Veuillez en choisir un autre.");
        }

        // Create object
        Character newCharacter = Character.builder()
                .name(requestDTO.getName())
                .build();

        return characterMapper.characterToCharacterResponseDTO(characterRepository.save(newCharacter));
    }

    public CharacterResponseDTO updateCharacter(Long id, UpdateCharacterRequestDTO requestDTO) {

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
        return characterMapper.characterToCharacterResponseDTO(updatedCharacter);
    }

    public void deleteCharacter(Long id) {
        if(characterRepository.existsById(id)) {
            characterRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Character not found with id : "+ id);
        }
    }
}
