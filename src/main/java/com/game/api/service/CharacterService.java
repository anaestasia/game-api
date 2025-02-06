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

    /* -- GET ALL -- */
    public List<CharacterResponseDTO> getAllCharacters() {
        List<Character> characters = characterRepository.findAll();
        if(characters.isEmpty()){
            throw new EntityNotFoundException("Character not found");
        } else {
            return characters.stream()
                    .map(characterMapper::characterToCharacterResponseDTO)
                    .collect(Collectors.toList());
        }
    }

    /* -- GET ALL WHERE NAME STARTING WITH -- */
    public List<CharacterResponseDTO> getCharactersWhereNameStartingWith(String prefix) {
        List<Character> characters = characterRepository.findAllByNameStartingWith(prefix);
        return characters.stream()
                .map(characterMapper::characterToCharacterResponseDTO)
                .collect(Collectors.toList());
    }

    /* -- GET BY ID -- */
    public CharacterResponseDTO getCharacterById(Long id) {
        Character character = characterRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Character not found with id : " + id));
        return characterMapper.characterToCharacterResponseDTO(character);
    }

    /* -- CREATE -- */
    public CharacterResponseDTO createCharacter(CreateCharacterRequestDTO requestDTO) {

        Optional<Character> existingCharacter = characterRepository.findByName(requestDTO.getName());
        if (existingCharacter.isPresent()) {
            throw new NameAlreadyTakenException("This name already exist. Please, choose another name.");
        }

        Character newCharacter = Character.builder()
                .name(requestDTO.getName())
                .build();

        return characterMapper.characterToCharacterResponseDTO(characterRepository.save(newCharacter));
    }

    /* -- UPDATE -- */
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

    /* -- DELETE -- */
    public void deleteCharacter(Long id) {
        Character character = characterRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Character not found with id: " + id));
        characterRepository.delete(character);
    }
}
