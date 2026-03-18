package com.jpa.concepts.jpa_concepts.DTO;

import com.jpa.concepts.jpa_concepts.Entity.Product;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class CartItemDto {
    private Long id;
    private ProductDto product; // Ideally ProductDto, but using Product for simplicity as per existing pattern
    // or changing to ProductDto if available.
    // Actually ProductDto doesn't exist yet? No, ProductController returns Product.
    // Wait, I should check if ProductDto exists.
    // I'll use Product for now or create ProductDto if needed.
    // Existing ProductController returns Product entity.
    private int quantity;
    // private Double totalPrice; // derived
}
