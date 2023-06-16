package de.bananabonanza.dto.create;

import de.bananabonanza.entity.Product;
import de.bananabonanza.entity.User;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WishlistCreate {

    @NotNull
    private Map<Product, Integer> items = new HashMap<>() {};

    @NotNull
    private List<User> sharedUsers = new ArrayList<>();
}
