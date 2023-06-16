package de.bananabonanza.dto.create;

import de.bananabonanza.entity.Product;
import jakarta.validation.constraints.NotNull;

import java.util.HashMap;
import java.util.Map;

public class WarehouseCreate {

    @NotNull
    private Map<Product, Integer> products = new HashMap<>() {};
}
