package com.jpa.concepts.jpa_concepts.DTO;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class CartDto {
    private Long id;
    private int cartTotalItems;
    private Double totalAmount;
    private UserDto user;
    private List<CartItemDto> cartItems = new ArrayList<>();
}
