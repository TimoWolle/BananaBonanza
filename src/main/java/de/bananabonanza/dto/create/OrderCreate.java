package de.bananabonanza.dto.create;

import de.bananabonanza.entity.Product;
import de.bananabonanza.entity.User;
import jakarta.validation.constraints.NotBlank;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Setter
public class OrderCreate {
    @NotBlank
    private User user;
    @NotBlank
    private Map<Product, Integer> items = new HashMap<>() {};

    private boolean paid ;

}
