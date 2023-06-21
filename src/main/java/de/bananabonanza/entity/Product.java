package de.bananabonanza.entity;

import de.bananabonanza.enumeration.ProductCategory;
import de.bananabonanza.enumeration.ProductStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    private String title;
    private String description;
    private String imageURL;
    private Double price;
    private Double oldPrice;
    @Enumerated(EnumType.STRING)
    private ProductCategory category;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private List<Review> reviews = new ArrayList<>();
    @Enumerated(EnumType.STRING)
    private ProductStatus status;
}

