package com.game.api.entity;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name="items")
public @Data class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private EnumItemType type;

    @Column(name= "description", nullable = false)
    private String description;

    @Column(name = "bonus")
    private int bonus;

    @Enumerated(EnumType.STRING)
    @Column(name = "stat", nullable = false)
    private EnumStat stat;


}
