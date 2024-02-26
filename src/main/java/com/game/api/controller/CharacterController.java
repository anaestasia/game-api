package com.game.api.controller;

import com.game.api.dto.request.CreateCharacterDTO;
import com.game.api.dto.request.UpdateCharacterDTO;
import com.game.api.dto.response.CharacterResponseDTO;
import com.game.api.entity.Character;
import com.game.api.service.CharacterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/characters")
@Tag(name = "Character", description = "Character Controller")
public class CharacterController {

    @Autowired
    private CharacterService characterService;

    @Operation(
            summary = "Get all characters",
            description = "Get a list of all characters",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success"),
                    @ApiResponse(responseCode = "404", description = "Not found"),
            }
    )
    @GetMapping
    public ResponseEntity<List<Character>> getAllCharacters() {
        List<Character> characters = characterService.getAllCharacters();
        return new ResponseEntity<>(characters, HttpStatus.OK);
    }

    // TODO : SearchCharacterByName -> champ de saisie

    @Operation(
            summary = "Get one character by ID",
            description = "Get a character based on its ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success"),
                    @ApiResponse(responseCode = "404", description = "Not found"),
                    @ApiResponse(responseCode = "403", description = "Invalid request")
            })
    @GetMapping("/{id}")
    public ResponseEntity<Character> getCharacterById(@PathVariable Long id) {
        Character character = characterService.getCharacterById(id);
        return new ResponseEntity<>(character, HttpStatus.OK);
    }

    @Operation(
            summary = "Create a character",
            description = "Create a new character",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success"),
                    @ApiResponse(responseCode = "400", description = "Bad request")
            })
    @PostMapping
    public ResponseEntity<Character> createCharacter(@Valid @RequestBody CreateCharacterDTO requestDTO) {
        Character createdCharacter = characterService.createCharacter(requestDTO);
        return ResponseEntity.ok(createdCharacter);
    }

    @Operation(
            summary = "Update a character by ID",
            description = "Modify a character based on its ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success"),
                    @ApiResponse(responseCode = "404", description = "Not found"),
                    @ApiResponse(responseCode = "403", description = "Invalid request")
            })
    @PutMapping("/{id}")
    public ResponseEntity<CharacterResponseDTO> updateCharacter(@PathVariable Long id, @Valid @RequestBody UpdateCharacterDTO requestDTO) {
        CharacterResponseDTO updatedCharacter = characterService.updateCharacter(id, requestDTO);
        return ResponseEntity.ok(updatedCharacter);
    }

    // TODO : UpdateCharacterName -> DTO (destination user)

    @Operation(
            summary = "Delete a character by ID",
            description = "Delete a character based on its ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success"),
                    @ApiResponse(responseCode = "404", description = "Not found"),
                    @ApiResponse(responseCode = "403", description = "Invalid request")
            })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCharacter(@PathVariable Long id) {
        characterService.deleteCharacter(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
