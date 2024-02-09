package com.game.api.controller;

import com.game.api.entity.Character;
import com.game.api.service.CharacterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CharacterController {

    private CharacterService characterService;

    @GetMapping
    public ResponseEntity<Character> getCharacterById(@PathVariable Long id) {
        Character character = characterService.getCharacterById(id);
        return new ResponseEntity<>(character, HttpStatus.OK);
    }
}
