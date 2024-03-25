package com.game.api.controller;

import com.game.api.dto.request.CreateCharacterRequestDTO;
import com.game.api.dto.request.UpdateCharacterRequestDTO;
import com.game.api.dto.response.CharacterResponseDTO;
import com.game.api.service.CharacterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/characters")
@Tag(name = "Character", description = "Character Controller")
public class CharacterController {

    private final CharacterService characterService;

    /* -- GET ALL -- */
    @Operation(
            summary = "Get all characters",
            description = "Get a list of all characters",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success"),
                    @ApiResponse(responseCode = "404", description = "Not found"),
            }
    )
    @GetMapping
    public ResponseEntity<List<CharacterResponseDTO>> getAllCharacters() {
        List<CharacterResponseDTO> characters = characterService.getAllCharacters();
        return ResponseEntity.ok(characters);
    }

    /* -- GET ONE -- */
    @Operation(
            summary = "Get one character by ID",
            description = "Get a character based on its ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success"),
                    @ApiResponse(responseCode = "404", description = "Not found"),
                    @ApiResponse(responseCode = "403", description = "Invalid request")
            })
    @GetMapping("/{id}")
    public ResponseEntity<CharacterResponseDTO> getCharacterById(@PathVariable Long id) {
        CharacterResponseDTO character = characterService.getCharacterById(id);
        return new ResponseEntity<>(character, HttpStatus.OK);
    }

    /* -- GET ALL WHERE NAME STARTING WITH -- */
    // TODO : SearchCharacterWhereNameBeginBy('narv') -> champ de saisie / return 0,1 ou liste de character
    @Operation(
            summary = "Get all characters where name begin by 'prefix'",
            description = "Get a list of all characters where name begin by 'prefix'",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success"),
                    @ApiResponse(responseCode = "404", description = "Not found"),
            }
    )
    @GetMapping
    public ResponseEntity<List<CharacterResponseDTO>> getCharactersWhereNameStartingWith(String prefix) {
        List<CharacterResponseDTO> characters = characterService.getCharactersWhereNameStartingWith(prefix);
        return ResponseEntity.ok(characters);
    }

    /* -- CREATE -- */
    @Operation(
            summary = "Create a character",
            description = "Create a new character",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success"),
                    @ApiResponse(responseCode = "400", description = "Bad request")
            })
    @PostMapping
    public ResponseEntity<CharacterResponseDTO> createCharacter(@Valid @RequestBody CreateCharacterRequestDTO requestDTO) {
        CharacterResponseDTO createdCharacter = characterService.createCharacter(requestDTO);
        return ResponseEntity.ok(createdCharacter);
    }

    /* -- UPDATE (DESTINATION ADMIN) -- */
    @Operation(
            summary = "Update a character by ID",
            description = "Modify a character based on its ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success"),
                    @ApiResponse(responseCode = "404", description = "Not found"),
                    @ApiResponse(responseCode = "403", description = "Invalid request")
            })
    @PutMapping("/{id}")
    public ResponseEntity<CharacterResponseDTO> updateCharacter(@PathVariable Long id, @Valid @RequestBody UpdateCharacterRequestDTO requestDTO) {
        CharacterResponseDTO updatedCharacter = characterService.updateCharacter(id, requestDTO);
        return ResponseEntity.ok(updatedCharacter);
    }

    /* -- DELETE -- */
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
