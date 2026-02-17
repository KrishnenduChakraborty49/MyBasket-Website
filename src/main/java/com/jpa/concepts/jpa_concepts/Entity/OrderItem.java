package com.jpa.concepts.jpa_concepts.Entity;

import jakarta.persistence.*;

@Entity
@Table(name="my-basket-order-items")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private Product product;

    private Integer quantity;

    @ManyToOne
    private Order order;
}
