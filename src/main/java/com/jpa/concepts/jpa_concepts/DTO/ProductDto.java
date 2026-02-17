package com.jpa.concepts.jpa_concepts.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private int productId;
    @NotBlank(message="Product title is required")
    @Size(min=5,message="Product title must be gretter than 5 character")
    private String title;
    @NotBlank(message="Product description is required")
    private String description;
    @Min(value=1,message="Price of product must be gretter than 1")
    private double price;
    private boolean live;
    private boolean outOfStock;
    private String imageUri;
}
