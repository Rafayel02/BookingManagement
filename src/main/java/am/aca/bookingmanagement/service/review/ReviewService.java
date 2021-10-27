package am.aca.bookingmanagement.service.review;

import am.aca.bookingmanagement.entity.Review;

import java.util.List;

public interface ReviewService {

    Review create(Review review);

    List<Review> getAllReviewsByPartnerId(Long id);

    List<Review> getAllReviewsByUserId(Long id);

}