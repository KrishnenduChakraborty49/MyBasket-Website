package com.jpa.concepts.jpa_concepts.Entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="my-basket-cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int cartTotalItems;

    private double totalAmount;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @OneToMany(mappedBy = "cart",fetch=FetchType.LAZY)
    private List<CartItem> cartitem=new ArrayList<>();
}
