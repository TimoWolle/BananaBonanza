package de.bananabonanza.dto.update;

import de.bananabonanza.entity.User;
import de.bananabonanza.enumeration.Rating;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class ReviewUpdate {

    @NotNull
    @Positive
    private Long id;
    @NotBlank
    private String titel;
    @NotBlank
    private String description;
    @NotBlank
    private User author;
    @NotBlank
    private Rating rating;
}
