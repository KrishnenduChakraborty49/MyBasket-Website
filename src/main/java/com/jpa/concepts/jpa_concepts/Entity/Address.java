package com.jpa.concepts.jpa_concepts.Entity;

import jakarta.persistence.*;

@Entity
@Table(name="my-basket-address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String street;
    private String city;
    private String state;
    private String pincode;
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
}
