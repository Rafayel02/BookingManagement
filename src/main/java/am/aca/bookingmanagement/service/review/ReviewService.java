package am.aca.bookingmanagement.service.review;

import am.aca.bookingmanagement.entity.Review;
import am.aca.bookingmanagement.entity.User;

import java.util.List;
import java.util.Optional;

public interface ReviewService {

    Optional<User> findUserById(Long id);

    Review create(Review review);

    List<Review> getAllReviewsByPartnerId(Long id);

}