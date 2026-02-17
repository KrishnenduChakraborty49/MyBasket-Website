package com.jpa.concepts.jpa_concepts.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name="my-basket-products")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private Long productId;
    private String title;
    private String description;
    private double price;
    private boolean live;
    private boolean outOfStock;
    private String imageUri;
    @ManyToMany
    private Set<Category> categories=new LinkedHashSet<>();
    @OneToOne(fetch=FetchType.EAGER,cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name = "image_id", nullable = true)
    private FileMetaData image;
}
