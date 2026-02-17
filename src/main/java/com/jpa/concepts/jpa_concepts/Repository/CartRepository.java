package com.jpa.concepts.jpa_concepts.Repository;

import com.jpa.concepts.jpa_concepts.Entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart,Long> {
}
