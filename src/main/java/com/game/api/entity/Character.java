package com.game.api.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name="characters")
public @Data class Character {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    // TODO : Add "race" field

    @Column(name = "pv")
    private int pv;

    @Column(name = "atq")
    private int atq;

    @Column(name = "def")
    private int def;

    @Column(name = "speed")
    private int speed;

    @ManyToMany
    @JoinTable(name = "characters_slots",
        joinColumns = @JoinColumn(name = "character_id"),
        inverseJoinColumns = @JoinColumn(name = "slot_id"))
    private Set<Slot> slots = new HashSet<>();
}
