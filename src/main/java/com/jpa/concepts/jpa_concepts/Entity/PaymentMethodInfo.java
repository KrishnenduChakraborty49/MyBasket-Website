package com.jpa.concepts.jpa_concepts.Entity;

import jakarta.persistence.*;

@Entity
@Table(name="my-basket-payment-method-info")
public class PaymentMethodInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY)
    private User user;

    @OneToOne
    private Payment payment;

    @Embedded
    private Upi upi;

    @Embedded
    private Cod cod;
}
