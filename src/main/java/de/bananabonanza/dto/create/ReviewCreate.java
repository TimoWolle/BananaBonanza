package de.bananabonanza.dto.create;

import de.bananabonanza.entity.User;
import de.bananabonanza.enumeration.Rating;
import jakarta.validation.constraints.NotBlank;

public class ReviewCreate {
    @NotBlank
    private String titel;
    @NotBlank
    private String description;
    @NotBlank
    private User author;
    @NotBlank
    private Rating rating;
}
