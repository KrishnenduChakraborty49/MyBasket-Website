package com.jpa.concepts.jpa_concepts.Entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="my-basket-order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch=FetchType.LAZY)
    private Address address;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus=OrderStatus.PLACED;


    @OneToMany(mappedBy="order",fetch=FetchType.EAGER,cascade = CascadeType.ALL)
    private List<OrderItem> orderItems=new ArrayList<>();

    @OneToOne(mappedBy = "order")
    private Payment payment;
}
