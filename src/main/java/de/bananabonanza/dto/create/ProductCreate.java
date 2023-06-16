package de.bananabonanza.dto.create;

import jakarta.validation.constraints.NotBlank;

public class ProductCreate {
    @NotBlank
    private String country;
    @NotBlank
    private String postalCode;
    @NotBlank
    private String city;
    @NotBlank
    private String street;
    @NotBlank
    private String housenumber;
    @NotBlank
    private Boolean activeAddress;
}
