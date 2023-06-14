package de.bananabonanza.dto;

import de.bananabonanza.entity.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Getter
@NoArgsConstructor
public class ShoppingCartItem {
    private Map<Product,Integer> shoppingCart;

}
