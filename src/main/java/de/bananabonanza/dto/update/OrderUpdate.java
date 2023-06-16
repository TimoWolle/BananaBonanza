package de.bananabonanza.dto.update;

import de.bananabonanza.entity.Payment;
import de.bananabonanza.entity.Product;
import de.bananabonanza.entity.User;
import de.bananabonanza.enumeration.DeliveryStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class OrderUpdate {

    @NotNull
    @Positive
    private Long id;
    @NotBlank
    private User user;
    @NotBlank
    private Map<Product, Integer> items = new HashMap<>() {};
    @NotBlank
    private Payment paymentMethod;
    @NotBlank
    private Boolean paid;
    @NotBlank
    private DeliveryStatus deliveryStatus;
    private LocalDateTime buydate;
    private LocalDateTime lastedit;
}
