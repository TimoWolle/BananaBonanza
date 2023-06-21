package de.bananabonanza.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode

@Entity
public class Wishlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    private Map<Product, Integer> items = new HashMap<>() {};

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private List<User> sharedUsers = new ArrayList<>();

    @JsonIgnore
    public Map<Product, Integer> getItems() {
        return items;
    }

    @JsonProperty("items")
    public List<ProductCount> getItemsList() {
        return items.entrySet().stream()
                .map(entry -> new ProductCount(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }
}
