package am.aca.bookingmanagement.service.reviewservice;

import am.aca.bookingmanagement.entity.Review;
import am.aca.bookingmanagement.entity.User;

import java.util.Optional;

public interface ReviewService {

    Optional<User> findUserById(Long id);

    Review create(Review review);

}
