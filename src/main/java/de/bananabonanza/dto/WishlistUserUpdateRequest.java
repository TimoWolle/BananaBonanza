package de.bananabonanza.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class WishlistUserUpdateRequest {
    @NotNull
    @Positive
    private Long id;
    @NotBlank
    private String userEmail;
    @NotBlank
    private String action;

}
