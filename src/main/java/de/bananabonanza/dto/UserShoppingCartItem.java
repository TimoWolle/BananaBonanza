package de.bananabonanza.dto;

import de.bananabonanza.entity.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserShoppingCartItem {
    @NotNull
    private Product product;
    @NotNull
    private Integer quantity;
    @NotBlank
    private String action;

}
