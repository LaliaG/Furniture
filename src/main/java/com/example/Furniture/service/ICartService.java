package com.example.Furniture.service;

import com.example.Furniture.entity.CartItem;

import java.util.List;

public interface ICartService {

    List<CartItem> getAllCartItems();
    void addToCart(Long furnitureId);

   /* CartItem addToCart(CartItem cartItem);*/
    void removeFromCart(Long id);
    void clearCart();


}
