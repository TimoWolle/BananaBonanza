package de.bananabonanza.dto.update;

import de.bananabonanza.entity.Address;
import de.bananabonanza.entity.Payment;
import de.bananabonanza.entity.Product;
import de.bananabonanza.entity.Wishlist;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserUpdate {

    @NotNull
    @Positive
    private Long id;
    @NotBlank
    private String firstname;
    @NotBlank
    private String lastname;
    @NotBlank
    private String email;
    @NotBlank
    private String passwort;

    private List<Address> addresses = new ArrayList<>();

    private Map<Product, Integer> shoppingCart = new HashMap<>();

    private List<Wishlist> wishlist = new ArrayList<>();

    private List<Product> saveforlaterlist = new ArrayList<>();

    private List<Payment> payments = new ArrayList<>();
}
