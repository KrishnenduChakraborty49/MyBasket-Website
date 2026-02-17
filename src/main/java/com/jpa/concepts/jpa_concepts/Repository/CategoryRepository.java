package com.jpa.concepts.jpa_concepts.Repository;

import com.jpa.concepts.jpa_concepts.Entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {

}
