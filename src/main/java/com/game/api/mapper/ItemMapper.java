package com.game.api.mapper;

import com.game.api.dto.response.ItemResponseDTO;
import com.game.api.entity.Item;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface ItemMapper {
    ItemResponseDTO itemToItemResponseDTO(Item item);
}
