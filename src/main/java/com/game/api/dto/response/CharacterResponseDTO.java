package com.game.api.dto.response;

import com.game.api.entity.Slot;
import lombok.*;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CharacterResponseDTO {

    private Long id;
    private String name;
    private int pv;
    private int atq;
    private int def;
    private int speed;
    private Set<Slot> slots;

}
