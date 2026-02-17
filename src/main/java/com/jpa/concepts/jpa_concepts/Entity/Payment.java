package com.jpa.concepts.jpa_concepts.Entity;

import jakarta.persistence.*;

@Entity
@Table(name="my-basket-payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name="order_id")
    private Order order;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus=PaymentStatus.PENDING;

    @Enumerated(EnumType.STRING)
    private PaymentMethod method=PaymentMethod.COD;

    @OneToOne(mappedBy="payment")
    private PaymentMethodInfo paymentMethodInfo;
}
