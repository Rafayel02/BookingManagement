package am.aca.bookingmanagement.service.review;

import org.springframework.stereotype.Service;
import am.aca.bookingmanagement.entity.User;
import am.aca.bookingmanagement.entity.Review;
import am.aca.bookingmanagement.service.user.UserService;
import am.aca.bookingmanagement.repository.ReviewRepository;
import am.aca.bookingmanagement.exception.DuplicateReviewException;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final UserService userService;
    private final ReviewRepository reviewRepository;

    public ReviewServiceImpl(final UserService userService,
                             final ReviewRepository reviewRepository) {
        this.userService = userService;
        this.reviewRepository = reviewRepository;
    }

    @Override
    public Optional<User> findUserById(final Long id) {
        return userService.findById(id);
    }

    @Override
    public Review create(final Review review) {
        final Optional<Review> foundReview = reviewRepository.findReviewByUser_IdAndPartner_Id(
                review.getUser().getId(),
                review.getPartner().getId()
        );

        if (foundReview.isPresent()) {
            throw new DuplicateReviewException();
        }
        return reviewRepository.save(review);
    }

    @Override
    public List<Review> getAllReviewsByPartnerId(final Long id) {
        return reviewRepository.findReviewsByPartner_Id(id);
    }

}