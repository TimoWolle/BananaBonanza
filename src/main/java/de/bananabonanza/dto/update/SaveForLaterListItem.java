package de.bananabonanza.dto.update;

import de.bananabonanza.entity.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SaveForLaterListItem {
    @NotNull
    private Product product;
    @NotBlank
    private String action;

}
