package com.jpa.concepts.jpa_concepts.controller;

import com.jpa.concepts.jpa_concepts.DTO.PageResponse;
import com.jpa.concepts.jpa_concepts.DTO.ProductDto;
import com.jpa.concepts.jpa_concepts.Entity.FileMetaData;
import com.jpa.concepts.jpa_concepts.Entity.Product;
import com.jpa.concepts.jpa_concepts.Repository.ProductRepository;
import com.jpa.concepts.jpa_concepts.service.FileStorageService;
import com.jpa.concepts.jpa_concepts.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productServices;

    private final FileStorageService fileStorageService;

//    //public ProductController(ProductService productServices){
//        this.productServices=productServices;
//    }
//
//    @GetMapping
//    public List<Product> getProducts(){
//
//        return productServices.getAll();
//    }
    @GetMapping
    public PageResponse<Product> getProducts(
            @RequestParam(value="page",defaultValue = "0") int page,
            @RequestParam(value="size",defaultValue = "10")int size,
            @RequestParam("sortBy") String sortBy,
            @RequestParam(value="sortDir",defaultValue = "asc")String sortDir
    ){
        return productServices.getAll(page,size,sortBy,sortDir);
    }
    @GetMapping("/{productId}")
    public Product getSingleProduct(@PathVariable("productId") Long productId){
//        Product product=productServices.get(Math.toIntExact(productId)).orElseThrow(()->new RuntimeException("product not found !!"));
        return productServices.get(productId);
    }
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProductDto> createProduct(@Valid @RequestBody ProductDto product){
        ProductDto savedEntity=productServices.createProduct(product);
        return new ResponseEntity<>(savedEntity, HttpStatus.CREATED);
    }
    @PutMapping("/{productId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Product> updateProduct(@PathVariable("productId") Long productId,@RequestBody Product product){
        Product savedEntity=productServices.updateProduct(productId,product);
        return new ResponseEntity<>(savedEntity,HttpStatus.OK);
    }
    @DeleteMapping("/{productId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable("productId") Long productId){
        productServices.delete(productId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
//    @PostMapping("/{productId}/image")
//    public ResponseEntity<FileMetaData> uploadProductImage(
//            @PathVariable("productId") Long productId,
//            @RequestParam("productImage")MultipartFile file
//    )throws IOException {
//        //System.out.println(file.getOriginalFilename());
//        Product product=productServices.get(productId);
//        FileMetaData fileMetaData=fileStorageService.uploadFile(file);
//        product.setImage(fileMetaData);
//        //image validation
//        if(!file.getContentType().equalsIgnoreCase("image/png") && !file.getContentType().equalsIgnoreCase("image/jpeg")){
//            throw new BadRequestException("Invalid file type!");
//        }
//        //file upload code
//        productServices.updateProduct(productId,product);
//        return ResponseEntity.ok(fileMetaData);
//    }
    @PostMapping("/{productId}/image")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<FileMetaData> uploadProductImage(
            @PathVariable Long productId,
            @RequestParam("productImage") MultipartFile file
    ) throws BadRequestException {

        if (!file.getContentType().startsWith("image/")) {
            throw new BadRequestException("Invalid image type");
        }

        Product product = productServices.get(productId);
        FileMetaData fileMetaData = fileStorageService.uploadFile(file);

        product.setImage(fileMetaData);
        productServices.updateProduct(productId, product);

        return ResponseEntity.ok(fileMetaData);
    }

//    @GetMapping("/{productId}/image")
//    public ResponseEntity<Resource> serveFile(
//            @PathVariable("productId") Long productId
//    ) throws MalformedURLException, BadRequestException {
//        Product product=productServices.get(productId);
//        FileMetaData fileMetaData=product.getImage();
//        Resource resource=fileStorageService.loadFile(fileMetaData);
//        String contentType= fileMetaData.getFileType();
//        return ResponseEntity.ok()
//                .contentType(MediaType.parseMediaType(contentType))
//                .header(
//                        HttpHeaders.CONTENT_DISPOSITION,
//                        "inline;filename=\""+resource.getFilename()+"\""
//                )
//                .body(resource);
//    }
}
