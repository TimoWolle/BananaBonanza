package de.bananabonanza.dto;

import de.bananabonanza.entity.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class WishlistItemUpdateRequest {
    @NotNull
    @Positive
    private Long id;
    @NotNull
    private Product product;
    @NotNull
    private Integer quantity;
    @NotBlank
    private String action;

}
