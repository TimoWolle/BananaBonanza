package de.bananabonanza.dto.update;

import de.bananabonanza.entity.Product;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.HashMap;
import java.util.Map;

public class WarehouseUpdate {

    @NotNull
    @Positive
    Long id;
    @NotEmpty
    private Map<Product, Integer> products = new HashMap<>() {};
}
