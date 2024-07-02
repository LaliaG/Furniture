package com.example.Furniture.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cartItem")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "furniture-id", nullable = false)
    @NotNull(message = "Furniture is mandatory")
    private Furniture furniture;

    @Column(name = "furniture_id", insertable = false, updatable = false)
    private Long furnitureId;


    private int quantity;
}
