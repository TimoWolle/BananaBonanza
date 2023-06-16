package de.bananabonanza.service;

import de.bananabonanza.entity.Review;
import de.bananabonanza.enumeration.Rating;
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

    public List<Review> getOneStarReviews(){
        return reviewRepository.findByRating(Rating.ONE_STAR);
    }

    public List<Review> getTwoStarReviews(){
        return reviewRepository.findByRating(Rating.TWO_STARS);
    }

    public List<Review> getThreeStarReviews(){
        return reviewRepository.findByRating(Rating.THREE_STARS);
    }

    public List<Review> getFourStarReviews(){
        return reviewRepository.findByRating(Rating.FOUR_STARS);
    }

    public List<Review> getFiveStarReviews(){
        return reviewRepository.findByRating(Rating.FIVE_STARS);
    }

    public long getCountOneStarReviews(){
        return reviewRepository.countByRating(Rating.ONE_STAR);
    }

    public long getCountTwoStarReviews(){
        return reviewRepository.countByRating(Rating.TWO_STARS);
    }

    public long getCountThreeStarReviews(){
        return reviewRepository.countByRating(Rating.THREE_STARS);
    }

    public long getCountFourStarReviews(){
        return reviewRepository.countByRating(Rating.FOUR_STARS);
    }

    public long getCountFiveStarReviews(){
        return reviewRepository.countByRating(Rating.FIVE_STARS);
    }
}

