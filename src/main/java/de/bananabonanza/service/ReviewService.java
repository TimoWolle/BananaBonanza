package de.bananabonanza.service;

import de.bananabonanza.entity.Review;
import de.bananabonanza.respository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    public Optional<Review> getReviewById(Long id) {
        return reviewRepository.findById(id);
    }

    public Review createReview(Review review) {
        return reviewRepository.save(review);
    }

    public Optional<Review> updateReview(Review updatedReview, Long id) {
        return reviewRepository.findById(id)
                .map(review -> {
                    review.setTitle(updatedReview.getTitle());
                    review.setDescription(updatedReview.getDescription());
                    review.setAuthor(updatedReview.getAuthor());
                    review.setRating(updatedReview.getRating());
                    reviewRepository.save(review);
                    return review;
                });
    }

    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }
}

