package com.game.api.dto.response;

import com.game.api.entity.EnumItemType;
import com.game.api.entity.Slot;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemResponseDTO {

    private Long id;
    private String name;
    private EnumItemType type;
    private String description;
    private int bonus;
    private int stat;

}
