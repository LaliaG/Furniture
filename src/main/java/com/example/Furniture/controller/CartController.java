package com.example.Furniture.controller;


import com.example.Furniture.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cart")
public class CartController {
    private final ICartService cartService;

    @Autowired
    public CartController(ICartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/list")
    public String listCartItems(Model model) {
        model.addAttribute("cartItems", cartService.getAllCartItems());
        return "cart";
    }


    @PostMapping("/add/{furnitureId}")
    public String addToCart(@PathVariable Long furnitureId) {
        cartService.addToCart(furnitureId);
        return "redirect:/cart/items";
    }

    @PostMapping("/remove/{id}")
    public String removeFromCart(@PathVariable Long id) {
        cartService.removeFromCart(id);
        return "redirect:/cart/items";
    }

    @PostMapping("/clear")
    public String clearCart() {
        cartService.clearCart();
        return "redirect:/cart/items";
    }
}
