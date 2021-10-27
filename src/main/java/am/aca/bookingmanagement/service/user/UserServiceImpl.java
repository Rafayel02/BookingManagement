package am.aca.bookingmanagement.service.user;

import am.aca.bookingmanagement.entity.Review;
import am.aca.bookingmanagement.entity.User;
import am.aca.bookingmanagement.exception.UserAlreadyExistsException;
import am.aca.bookingmanagement.repository.UserRepository;
import am.aca.bookingmanagement.service.review.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ReviewService reviewService;

    public UserServiceImpl(final UserRepository userRepository, final ReviewService reviewService) {
        this.userRepository = userRepository;
        this.reviewService = reviewService;
    }

    @Override
    public User create(final User user) {
        final Optional<User> byEmail = findByEmail(user.getEmail());
        if (byEmail.isPresent()) {
            throw new UserAlreadyExistsException();
        }
        return userRepository.save(user);
    }

    public Optional<User> findByEmail(final String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<User> findById(final Long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<Review> getAllReviews(final Long id) {
        return reviewService.getAllReviewsByUserId(id);
    }

}