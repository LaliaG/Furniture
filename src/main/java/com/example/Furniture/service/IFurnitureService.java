package com.example.Furniture.service;

import com.example.Furniture.entity.Furniture;

import java.util.List;

public interface IFurnitureService {

    List<Furniture> getAllFurniture();
    Furniture saveFurniture(Furniture furniture);
    Furniture getFurnitureById(Long id);
    void deleteFurniture(Long id);








}
