package com.example.demo;

import jakarta.persistence.*;

@Entity
@Table(name = "new_entity")

public class NewEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "new_entity_seq")
    @SequenceGenerator(name = "new_entity_seq")
    @Column(name = "id", nullable = false)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "id: " + id.toString();
    }
}