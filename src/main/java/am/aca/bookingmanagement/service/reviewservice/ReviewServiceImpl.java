package am.aca.bookingmanagement.service.reviewservice;

import am.aca.bookingmanagement.entity.Partner;
import am.aca.bookingmanagement.entity.Review;
import am.aca.bookingmanagement.entity.User;
import am.aca.bookingmanagement.repository.PartnerRepository;
import am.aca.bookingmanagement.repository.ReviewRepository;
import am.aca.bookingmanagement.service.partnerservice.PartnerService;
import am.aca.bookingmanagement.service.userservice.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final UserService userService;
    private final ReviewRepository reviewRepository;

    public ReviewServiceImpl(final UserService userService,
                             final PartnerService partnerService,
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
        return reviewRepository.save(review);
    }

}