package de.bananabonanza.service;

import de.bananabonanza.entity.Address;
import de.bananabonanza.entity.Review;
import de.bananabonanza.enumeration.Rating;
import de.bananabonanza.respository.AddressRepository;
import de.bananabonanza.respository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    @Transactional
    public Review saveReview(Review review) {
        return reviewRepository.save(review);
    }

    @Transactional(readOnly = true)
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Review> getReviewById(Long id) {
        return reviewRepository.findById(id);
    }

    @Transactional
    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }

    @Transactional
    public Review updateReview(Review _review) {
        Review existingReview = getReviewById(_review.getId()).orElseThrow(() -> new IllegalArgumentException("Review with the id " + _review.getId() + " not found."));

        existingReview.setTitle(_review.getTitle());
        existingReview.setDescription(_review.getDescription());
        existingReview.setAuthor(_review.getAuthor());
        existingReview.setRating(_review.getRating());

        return reviewRepository.save(existingReview);
    }

    @Transactional(readOnly = true)
    public List<Review> getOneStarReviews(){
        return reviewRepository.findByRating(Rating.ONE_STAR);
    }

    @Transactional(readOnly = true)
    public List<Review> getTwoStarReviews(){
        return reviewRepository.findByRating(Rating.TWO_STARS);
    }

    @Transactional(readOnly = true)
    public List<Review> getThreeStarReviews(){
        return reviewRepository.findByRating(Rating.THREE_STARS);
    }

    @Transactional(readOnly = true)
    public List<Review> getFourStarReviews(){
        return reviewRepository.findByRating(Rating.FOUR_STARS);
    }

    @Transactional(readOnly = true)
    public List<Review> getFiveStarReviews(){
        return reviewRepository.findByRating(Rating.FIVE_STARS);
    }

    @Transactional(readOnly = true)
    public long getCountOneStarReviews(){
        return reviewRepository.countByRating(Rating.ONE_STAR);
    }

    @Transactional(readOnly = true)
    public long getCountTwoStarReviews(){
        return reviewRepository.countByRating(Rating.TWO_STARS);
    }

    @Transactional(readOnly = true)
    public long getCountThreeStarReviews(){
        return reviewRepository.countByRating(Rating.THREE_STARS);
    }

    @Transactional(readOnly = true)
    public long getCountFourStarReviews(){
        return reviewRepository.countByRating(Rating.FOUR_STARS);
    }

    @Transactional(readOnly = true)
    public long getCountFiveStarReviews(){
        return reviewRepository.countByRating(Rating.FIVE_STARS);
    }
}

