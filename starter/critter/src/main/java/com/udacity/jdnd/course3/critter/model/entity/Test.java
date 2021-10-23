package com.udacity.jdnd.course3.critter.model.entity;

import javax.persistence.*;
import lombok.*;

@Setter
@Getter

@Entity
public class Test {
	@Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
}
