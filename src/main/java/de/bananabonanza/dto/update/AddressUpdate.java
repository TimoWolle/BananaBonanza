package de.bananabonanza.dto.update;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class AddressUpdate {

    @NotNull
    @Positive
    private Long id;
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
