package com.example.Furniture.service;

import com.example.Furniture.dao.CartItemRepository;
import com.example.Furniture.dao.FurnitureRepository;
import com.example.Furniture.entity.CartItem;
import com.example.Furniture.entity.Furniture;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService implements ICartService{
    private final CartItemRepository cartItemRepository;
    private final FurnitureRepository furnitureRepository;

    @Autowired
    public CartService(CartItemRepository cartItemRepository, FurnitureRepository furnitureRepository) {
        this.cartItemRepository = cartItemRepository;
        this.furnitureRepository = furnitureRepository;
    }

    @Transactional
    public void addCartItem(CartItem cartItem) {
        cartItemRepository.save(cartItem);
    }

    @Override
    public List<CartItem> getAllCartItems() {
        return cartItemRepository.findAll();
    }

    @Override
    public void addToCart(Long furnitureId) {
        Furniture furniture = furnitureRepository.findById(furnitureId).orElseThrow();
        if (furniture.getStock() > 0) {
            furniture.setStock(furniture.getStock() - 1);
            furnitureRepository.save(furniture);

            CartItem cartItem = new CartItem();
            cartItem.setFurniture(furniture);
            cartItem.setQuantity(1);
            cartItemRepository.save(cartItem);
        }
    }

    @Override
    public void removeFromCart(Long id) {
        CartItem cartItem = cartItemRepository.findById(id).orElseThrow();
        Furniture furniture = cartItem.getFurniture();
        furniture.setStock(furniture.getStock() + 1);
        furnitureRepository.save(furniture);

        cartItemRepository.deleteById(id);
    }

    @Override
    public void clearCart() {
        List<CartItem> cartItems = cartItemRepository.findAll();
        for (CartItem cartItem : cartItems) {
            Furniture furniture = cartItem.getFurniture();
            furniture.setStock(furniture.getStock() + cartItem.getQuantity());
            furnitureRepository.save(furniture);
        }
        cartItemRepository.deleteAll();
    }
}
