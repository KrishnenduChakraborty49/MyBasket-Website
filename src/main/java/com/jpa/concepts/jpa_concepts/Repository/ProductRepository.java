package com.jpa.concepts.jpa_concepts.Repository;

import com.jpa.concepts.jpa_concepts.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Integer> {


    //method 2 of declaring a query outside the crud operation and do not do this implementation(automatically happen)
    List<Product> findByTitle(String title);
    List<Product> findByLive(boolean live);
    List<Product> findByOutOfStock(boolean outOfStock);
    Optional<Product> findByProductIdAndTitle(long productId,String title);

    //method-2 by declaring the jakarta persitantent query language like sql
    @Query(" Select p from Product p")
    List<Product> getAllProduct();



}
