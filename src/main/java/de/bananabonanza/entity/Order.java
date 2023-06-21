package de.bananabonanza.entity;

import de.bananabonanza.enumeration.DeliveryStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@EqualsAndHashCode
@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private User user;
    @ElementCollection
    private Map<Product, Integer> items = new HashMap<>() {};
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Payment paymentMethod;
    private Boolean paid;
    private DeliveryStatus deliveryStatus;
    private LocalDateTime buydate;
    private LocalDateTime lastedit;
}
