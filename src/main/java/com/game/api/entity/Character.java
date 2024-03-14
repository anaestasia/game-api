package com.game.api.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="characters")
public class Character {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    // TODO : Add "race"

    @Builder.Default
    @Column(name = "pv")
    private int pv = 10;

    @Builder.Default
    @Column(name = "atq")
    private int atq = 2;

    @Builder.Default
    @Column(name = "def")
    private int def = 0;

    @Builder.Default
    @Column(name = "speed")
    private int speed = 1;

    @ManyToMany
    @JoinTable(name = "characters_slots",
        joinColumns = @JoinColumn(name = "character_id"),
        inverseJoinColumns = @JoinColumn(name = "slot_id"))
    private Set<Slot> slots = new HashSet<>();
}
