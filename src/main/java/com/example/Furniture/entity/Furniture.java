package com.example.Furniture.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "furniture")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Furniture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "furniture_name")
    @NotBlank(message = "Name is mandatory")
    @Size(max = 100, message = "Name should not exceed 100 characters")
    private String name;

    @Size(max = 255, message = "Description should not exceed 255 characters")
    private String description;

    @NotNull(message = "Price is mandatory")
    @Min(value = 0, message = "Price should not be less than 0")
    private double price;

    @NotNull(message = "Stock is mandatory")
    @Min(value = 0, message = "Stock should not be less than 0")
    private int stock;

    @OneToMany(mappedBy = "furniture", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> cartItems;


}
