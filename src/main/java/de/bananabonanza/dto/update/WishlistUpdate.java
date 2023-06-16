package de.bananabonanza.dto.update;

import de.bananabonanza.entity.Product;
import de.bananabonanza.entity.User;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WishlistUpdate {

    @NotNull
    @Positive
    private Long id;

    @NotEmpty
    private Map<Product, Integer> items = new HashMap<>() {};

    @NotEmpty
    private List<User> sharedUsers = new ArrayList<>();
}
