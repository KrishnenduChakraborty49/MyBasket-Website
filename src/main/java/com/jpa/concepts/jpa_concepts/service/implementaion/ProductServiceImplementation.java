package com.jpa.concepts.jpa_concepts.service.implementaion;

import com.jpa.concepts.jpa_concepts.DTO.PageResponse;
import com.jpa.concepts.jpa_concepts.DTO.ProductDto;
import com.jpa.concepts.jpa_concepts.Entity.Product;
import com.jpa.concepts.jpa_concepts.Repository.ProductRepository;
import com.jpa.concepts.jpa_concepts.service.ProductService;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;

import static org.springframework.data.domain.PageRequest.of;

@Service
@RequiredArgsConstructor
public class ProductServiceImplementation implements ProductService {

    private  final ProductRepository productRepository;

    private final ModelMapper modelMapper;


    @Override
    public ProductDto createProduct(ProductDto productdto) {
        var product=modelMapper.map(productdto,Product.class);
        var savedproduct=productRepository.save(product);
        return modelMapper.map(savedproduct,ProductDto.class);
    }

    @Override
    public Product updateProduct(Long productId, Product product) {
       var oldProduct=productRepository.findById(Math.toIntExact(productId)).orElseThrow(()->
               new RuntimeException("product is not found"));
       oldProduct.setTitle(product.getTitle());
       oldProduct.setDescription(product.getDescription());
       oldProduct.setPrice(product.getPrice());
       oldProduct.setLive(product.isLive());
       oldProduct.setOutOfStock(product.isOutOfStock());
       oldProduct.setImage(product.getImage());
       return productRepository.save(oldProduct);
    }

    @Override
    public PageResponse<Product> getAll(int page,int size,String sortBy,String sortDir) {
        Sort sort=sortDir.equalsIgnoreCase("desc")? Sort.by(sortBy).descending():Sort.by(sortBy).ascending();
        PageRequest pageable= of(page,size,sort);
        Page<Product> pageproducts=productRepository.findAll(pageable);
        return PageResponse.of(pageproducts);
    }

    @Override
    public Product get(Long productId) {
        return productRepository.findById(Math.toIntExact(productId)).orElseThrow(()->new RuntimeException("Product is not found"));
    }

    @Override
    public void delete(Long productId) {
        var product=productRepository.findById(Math.toIntExact(productId)).orElseThrow(()->new RuntimeException("product is not found"));
        productRepository.delete(product);
    }


}
