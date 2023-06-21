package de.bananabonanza.entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class ProductCount {
    private Product product;
    private Integer quantity;
}
