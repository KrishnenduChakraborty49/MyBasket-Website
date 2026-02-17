package com.jpa.concepts.jpa_concepts.service.implementaion;

import com.jpa.concepts.jpa_concepts.DTO.CategoryDto;
import com.jpa.concepts.jpa_concepts.Entity.Category;
import com.jpa.concepts.jpa_concepts.Entity.User;
import com.jpa.concepts.jpa_concepts.Repository.CategoryRepository;
import com.jpa.concepts.jpa_concepts.service.CategoryService;
import com.jpa.concepts.jpa_concepts.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImplementation implements CategoryService {

    private final CategoryRepository categoryRepository;

    private final ModelMapper modelMapper;




    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        //conversion from dto---->entity
        Category category=modelMapper.map(categoryDto,Category.class);
        var savedEntity=categoryRepository.save(category);
        // conversion from entity-->dto
        return modelMapper.map(savedEntity,CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(Long categoryId, CategoryDto categorydto) {
        Category category=modelMapper.map(categorydto,Category.class);
        var oldcategory=categoryRepository.findById(categoryId).orElseThrow(()->new RuntimeException("Category is not found"));
        oldcategory.setTitle(category.getTitle());
        oldcategory.setImageurl(category.getImageurl());
        var savedEntity=categoryRepository.save(oldcategory);
        return modelMapper.map(savedEntity,CategoryDto.class);
    }


    @Override
    public List<CategoryDto> getAll() {
        List<Category> categories= categoryRepository.findAll();
        return categories
                .stream()
                .map(category -> modelMapper.map(category,CategoryDto.class))
                .toList();
    }

    @Override
    public CategoryDto get(Long categoryId) {
        Category category= categoryRepository.findById(categoryId).orElseThrow(()->new RuntimeException("Category is not found"));
        return modelMapper.map(category,CategoryDto.class);
    }

    @Override
    public void deletecategory(Long categoryId) {
        Category category=categoryRepository.findById(categoryId).orElseThrow(()->new RuntimeException("Category is not found"));
        categoryRepository.delete(category);
    }
}
