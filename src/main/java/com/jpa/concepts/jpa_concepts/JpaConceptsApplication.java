package com.jpa.concepts.jpa_concepts;

import ch.qos.logback.core.encoder.JsonEscapeUtil;
import com.jpa.concepts.jpa_concepts.Entity.Product;
import com.jpa.concepts.jpa_concepts.Entity.User;
import com.jpa.concepts.jpa_concepts.Repository.ProductRepository;
import com.jpa.concepts.jpa_concepts.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JpaConceptsApplication implements CommandLineRunner {
    @Autowired
    public UserRepository userRepository;
    @Autowired
    public ProductRepository productRepository;
	public static void main(String[] args) {
		SpringApplication.run(JpaConceptsApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
//        System.out.println("app started");
        var user=new User();
       // user.setUserId(102);
        user.setName("RadheKrishna");
        user.setEmail("radhekrishna49@gmail.com");
        userRepository.save(user);
        userRepository.findAll();
//         var product1=new Product();
//
//         product1.setTitle("Picture of Radhe Krishna");
//         product1.setDescription("Gorgeous 18*12 inch Radhe Krishna Frame");
//         product1.setPrice(150.56);
//         product1.setLive(true);
//         product1.setOutOfStock(false);
//         productRepository.save(product1);
//         var product2=new Product();
//         product2.setTitle("iphone");
//         product2.setDescription("Amazing phone");
//         product2.setPrice(90000);
//         product2.setLive(true);
//         product2.setOutOfStock(false);
//         productRepository.save(product2);
//
//         productRepository.findAll();
//         productRepository.findByTitle("Picture of Radhe Krishna")
//                 .forEach(product -> {
//                     System.out.println(product.getTitle());
//                 });
//         productRepository.findByLive(true)
//                 .forEach(product ->{
//                     System.out.println(product.getDescription() +":"+product.getPrice());
//                 });
//         productRepository.findByOutOfStock(false)
//                 .forEach(product ->{
//                     System.out.println(product1.getDescription()+":"+product2.getPrice());
//                 });
//         productRepository.findByProductIdAndTitle(103,"Picture of Radhe Krishna")
//                 .ifPresentOrElse(product ->{
//                            System.out.println(product.getTitle()+":"+product.getPrice());
//                         }
//                         ,
//                         ()->{
//                             System.out.println("Product is not found");
//                         }
//                 );
//         User user=userRepository.findById(1).get();
//         userRepository.delete(user);
    }
}
