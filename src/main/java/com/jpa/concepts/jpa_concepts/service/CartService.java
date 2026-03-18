package com.jpa.concepts.jpa_concepts.service;

import com.jpa.concepts.jpa_concepts.DTO.CartDto;

public interface CartService {
    CartDto getCart(Integer userId);
    CartDto addToCart(Integer userId, Long productId, int quantity);
    void clearCart(Integer userId);
}
