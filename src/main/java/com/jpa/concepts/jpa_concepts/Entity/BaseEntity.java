package com.jpa.concepts.jpa_concepts.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

import java.util.Collection;

@MappedSuperclass
public class BaseEntity {

    @Column(nullable = false)
    private boolean deleted=false;


}
