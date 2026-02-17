package com.jpa.concepts.jpa_concepts.controller;

import com.jpa.concepts.jpa_concepts.DTO.CategoryDto;
import com.jpa.concepts.jpa_concepts.Entity.Category;
import com.jpa.concepts.jpa_concepts.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;



    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto category){
        CategoryDto savedEntity=categoryService.createCategory(category);
        return new ResponseEntity<>(savedEntity, HttpStatus.CREATED);
    }
    @PutMapping("/{categoryId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CategoryDto> updateCategory(@PathVariable("categoryId") Long categoryId,@RequestBody CategoryDto category){
        CategoryDto savedEntity=categoryService.updateCategory(categoryId,category);
        return new ResponseEntity<>(savedEntity,HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAll(){
        List<CategoryDto> list=categoryService.getAll();
        return new ResponseEntity<>(list,HttpStatus.OK);
    }
    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> get(@PathVariable ("categoryId") Long categoryId){
        CategoryDto categoryDto= categoryService.get(categoryId);
        return new ResponseEntity<>(categoryDto,HttpStatus.OK);
    }

    @DeleteMapping("/{categoryId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteCategory(@PathVariable("categoryId") Long categoryId){
        categoryService.deletecategory(categoryId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
