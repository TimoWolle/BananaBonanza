package de.bananabonanza.dto.update;

import de.bananabonanza.entity.Review;
import de.bananabonanza.enumeration.ProductCategory;
import de.bananabonanza.enumeration.ProductStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.ArrayList;
import java.util.List;

public class ProductUpdate {

    @NotNull
    @Positive
    private Long id;
    @NotBlank
    private String titel;
    @NotBlank
    private String description;
    @NotBlank
    private ProductCategory category;
    @NotBlank
    private List<Review> reviews = new ArrayList<>();
    @NotBlank
    private ProductStatus status;
}
