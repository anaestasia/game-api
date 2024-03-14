package com.game.api.dto.request;

import com.game.api.entity.Slot;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class UpdateCharacterRequestDTO {

    @NotBlank(message = "The name can't be empty")
    @Size(max = 50, message = "The name must have 50 characters max")
    private String name;

    @PositiveOrZero(message = "The PV must be positive or null")
    private int pv = 10;

    @PositiveOrZero(message = "ATQ must be positive or null")
    private int atq = 2;

    @PositiveOrZero(message = "DEF must be positive or null")
    private int def = 0;

    @NotNull(message = "La vitesse ne peut pas être nulle")
    @PositiveOrZero(message = "La vitesse doit être positive")
    private int speed = 1;

    private Set<Slot> slots = new HashSet<>();
}
