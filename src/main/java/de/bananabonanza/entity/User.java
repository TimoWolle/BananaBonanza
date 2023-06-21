package de.bananabonanza.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode

@Entity
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Views.Basic.class)
    private Long id;
    @JsonView(Views.Basic.class)
    private String firstname;
    @JsonView(Views.Basic.class)
    private String lastname;
    private String email;
    private String passwort;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private List<Address> addresses = new ArrayList<>();
    @ElementCollection(fetch = FetchType.EAGER)
    private Map<Product, Integer> shoppingCart = new HashMap<>();
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private List<Wishlist> wishlist = new ArrayList<>();
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private List<Product> saveforlaterlist = new ArrayList<>();
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private List<Payment> payments = new ArrayList<>();

    public User(String email, String encryptedPassword) {
        this.email = email;
        this.passwort = encryptedPassword;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.passwort;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @JsonIgnore
    public Map<Product, Integer> getShoppingCart() {
        return shoppingCart;
    }

    @JsonProperty("shoppingCart")
    public List<ProductCount> getShoppingCartList() {
        return shoppingCart.entrySet().stream()
                .map(entry -> new ProductCount(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }
}
