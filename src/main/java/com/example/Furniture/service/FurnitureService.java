package com.example.Furniture.service;

import com.example.Furniture.dao.FurnitureRepository;
import com.example.Furniture.entity.Furniture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class FurnitureService implements IFurnitureService{
    private final FurnitureRepository furnitureRepository;
    @Autowired
    public FurnitureService(FurnitureRepository furnitureRepository) {
        this.furnitureRepository = furnitureRepository;
    }

    @Override
    public List<Furniture> getAllFurniture() {
        return furnitureRepository.findAll();
    }

    @Override
    public Furniture saveFurniture(Furniture furniture) {
        return furnitureRepository.save(furniture);
    }

    @Override
    public Furniture getFurnitureById(Long id) {
        return furnitureRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteFurniture(Long id) {
        furnitureRepository.deleteById(id);

    }

}
