package de.bananabonanza.respository;

import de.bananabonanza.entity.Review;
import de.bananabonanza.enumeration.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByRating(Rating rating);
    long countByRating(Rating rating);
}
