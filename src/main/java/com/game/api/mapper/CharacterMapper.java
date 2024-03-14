package com.game.api.mapper;

import com.game.api.dto.response.CharacterResponseDTO;
import com.game.api.entity.Character;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CharacterMapper {

    CharacterMapper INSTANCE = Mappers.getMapper(CharacterMapper.class);

//    @Mapping(target = "id", source = "character.id")
//    @Mapping(target = "name", source = "character.name")
//    @Mapping(target = "pv", source = "character.pv")
//    @Mapping(target = "atq", source = "character.atq")
//    @Mapping(target = "def", source = "character.def")
//    @Mapping(target = "speed", source = "character.speed")
//    @Mapping(target = "slots", source = "character.slots")
    CharacterResponseDTO characterToCharacterResponseDTO(Character character);
}
