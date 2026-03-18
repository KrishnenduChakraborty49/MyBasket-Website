package com.jpa.concepts.jpa_concepts.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="my-basket-cart-item")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="product_id")
    private Product product;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="cart_id")
    private Cart cart;

    private int quantity;
}
