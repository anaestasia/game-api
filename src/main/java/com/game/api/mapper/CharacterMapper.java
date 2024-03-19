package com.game.api.mapper;

import com.game.api.dto.response.CharacterResponseDTO;
import com.game.api.entity.Character;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface CharacterMapper {
    CharacterResponseDTO characterToCharacterResponseDTO(Character character);
}
