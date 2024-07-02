package com.example.Furniture.controller;

import com.example.Furniture.entity.Furniture;
import com.example.Furniture.service.IFurnitureService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/furniture")
public class FurnitureController {
    private final IFurnitureService furnitureService;

    @Autowired
    public FurnitureController(IFurnitureService furnitureService) {
        this.furnitureService = furnitureService;
    }

    @GetMapping("/list")
    public String listFurniture(Model model) {
        model.addAttribute("furnitures", furnitureService.getAllFurniture());
        return "furniture-list";
    }

    @GetMapping("/form")
    public String showForm(@RequestParam(value = "id", required = false) Long id, Model model) {
        if (id != null) {
            model.addAttribute("furniture", furnitureService.getFurnitureById(id));
        } else {
            model.addAttribute("furniture", new Furniture());
        }
        return "furniture-form";
    }

    @PostMapping("/add")
    public String addFurniture(@Valid @ModelAttribute("furniture") Furniture furniture, BindingResult result) {
        if (result.hasErrors()) {
            return "furniture-form";
        }
        furnitureService.saveFurniture(furniture);
        return "redirect:/furniture/list";
    }

    @GetMapping("/delete")
    public String deleteFurniture(@RequestParam("id") Long id) {
        furnitureService.deleteFurniture(id);
        return "redirect:/furniture/list";
    }

    @GetMapping("/detail/{id}")
    public String showFurnitureDetail(@PathVariable("id") Long id, Model model) {
        Furniture furniture = furnitureService.getFurnitureById(id);
        model.addAttribute("furniture", furniture);
        return "furniture-detail";
    }
}
