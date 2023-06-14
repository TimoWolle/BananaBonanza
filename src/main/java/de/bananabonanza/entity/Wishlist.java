package de.bananabonanza.entity;

import de.bananabonanza.entity.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
public class Wishlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    private Map<Product, Integer> items = new HashMap<>() {};

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<User> sharedUsers = new ArrayList<>();
}
