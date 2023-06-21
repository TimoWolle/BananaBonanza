package de.bananabonanza.entity;

import de.bananabonanza.enumeration.ProductCategory;
import de.bananabonanza.enumeration.ProductStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String imageURL;
    private double price;
    private double oldPrice;
    @Enumerated(EnumType.STRING)
    private ProductCategory category;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private List<Review> reviews = new ArrayList<>();
    @Enumerated(EnumType.STRING)
    private ProductStatus status;

}

