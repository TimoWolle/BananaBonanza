package de.bananabonanza.entity;

import com.fasterxml.jackson.annotation.JsonView;
import de.bananabonanza.enumeration.Rating;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String description;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JsonView(Views.Basic.class)
    private User author;
    @Enumerated(EnumType.STRING)
    private Rating rating;
}
