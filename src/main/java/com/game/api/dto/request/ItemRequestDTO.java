package com.game.api.dto.request;

import com.game.api.entity.EnumItemType;
import com.game.api.entity.EnumStat;
import com.game.api.entity.Slot;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class ItemRequestDTO {

    @NotBlank(message = "The name can't be empty")
    @Size(max = 50, message = "The name must have 50 characters max")
    private String name;

    private EnumItemType type;

    @NotBlank(message = "The description can't be empty")
    private String description;

    @NotNull(message = "The bonus value can't be null")
    private int bonus;

    @NotBlank(message = "The stat can't be empty")
    private EnumStat stat;
}
