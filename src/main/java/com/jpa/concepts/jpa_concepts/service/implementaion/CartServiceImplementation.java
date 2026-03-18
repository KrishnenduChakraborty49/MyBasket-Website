package com.jpa.concepts.jpa_concepts.service.implementaion;

import com.jpa.concepts.jpa_concepts.DTO.CartDto;
import com.jpa.concepts.jpa_concepts.Entity.Cart;
import com.jpa.concepts.jpa_concepts.Entity.CartItem;
import com.jpa.concepts.jpa_concepts.Entity.Product;
import com.jpa.concepts.jpa_concepts.Entity.User;
import com.jpa.concepts.jpa_concepts.Exception.ResourceNotFoundException;
import com.jpa.concepts.jpa_concepts.Repository.CartRepository;
import com.jpa.concepts.jpa_concepts.Repository.ProductRepository;
import com.jpa.concepts.jpa_concepts.Repository.UserRepository;
import com.jpa.concepts.jpa_concepts.service.CartService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

@Service
@RequiredArgsConstructor
public class CartServiceImplementation implements CartService {
    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    @Override
    public CartDto getCart(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        Cart cart = cartRepository.findByUser(user)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found for user: " + userId));

        return modelMapper.map(cart, CartDto.class);
    }

    @Override
    @Transactional
    public CartDto addToCart(Integer userId, Long productId, int quantity) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        Product product = productRepository.findById(Math.toIntExact(productId))
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + productId));

        Cart cart = cartRepository.findByUser(user).orElseGet(() -> {
            Cart newCart = new Cart();
            newCart.setUser(user);
            newCart.setCartItems(new ArrayList<>());
            newCart.setTotalAmount(0.0);
            newCart.setCartTotalItems(0);
            return newCart;
        });

        // Check if item already exists in cart
        AtomicReference<Boolean> itemExists = new AtomicReference<>(false);

        cart.getCartItems().forEach(item -> {
            if (item.getProduct().getProductId().equals(product.getProductId())) {
                item.setQuantity(item.getQuantity() + quantity);
                // Also update price if needed, but assuming simple quantity update
                // item.setTotalPrice... (if CartItem has price)
                itemExists.set(true);
            }
        });

        if (!itemExists.get()) {
            CartItem cartItem = CartItem.builder()
                    .cart(cart)
                    .product(product)
                    .quantity(quantity)
                    .build();
            cart.getCartItems().add(cartItem);
        }

        // Recalculate totals
        // Note: This is a simplified logic. In real world, we need to handle prices
        // carefully.
        // Assuming Product has price.
        double totalAmount = cart.getCartItems().stream()
                .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum();

        int totalItems = cart.getCartItems().stream()
                .mapToInt(CartItem::getQuantity)
                .sum();

        cart.setTotalAmount(totalAmount);
        cart.setCartTotalItems(totalItems);

        Cart savedCart = cartRepository.save(cart);

        return modelMapper.map(savedCart, CartDto.class);
    }
    @Override
    @Transactional
    public void clearCart(Integer userId) {

        // find user
        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found with id: " + userId));

        // find cart
        Cart cart = cartRepository.findByUser(user)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Cart not found for user: " + userId));

        // clear cart items
        cart.getCartItems().clear();

        // reset totals
        cart.setTotalAmount(0.0);
        cart.setCartTotalItems(0);

        // save updated cart
        cartRepository.save(cart);
    }
}
