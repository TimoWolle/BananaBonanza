package de.bananabonanza.dto;

import de.bananabonanza.entity.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class UserShoppingCartItem {
    @NotNull
    private Product product;
    @NotNull
    private Integer quantity;
    @NotBlank
    private String action;

}
