package com.game.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

public @Data class CreateCharacterRequestDTO {

    @NotBlank(message = "The name can't be empty")
    @Size(max = 50, message = "The name must have 50 characters max")
    private String name;
}
