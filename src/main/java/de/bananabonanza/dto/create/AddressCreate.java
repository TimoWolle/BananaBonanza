package de.bananabonanza.dto.create;

import de.bananabonanza.entity.Review;
import de.bananabonanza.enumeration.ProductCategory;
import de.bananabonanza.enumeration.ProductStatus;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

public class AddressCreate {
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
