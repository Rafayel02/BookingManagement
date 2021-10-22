package am.aca.bookingmanagement.service.reviewservice;

import am.aca.bookingmanagement.entity.Partner;
import am.aca.bookingmanagement.entity.Review;
import am.aca.bookingmanagement.entity.User;
import am.aca.bookingmanagement.repository.ReviewRepository;
import am.aca.bookingmanagement.service.partnerservice.PartnerService;
import am.aca.bookingmanagement.service.userservice.UserService;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final UserService userService;
    private final PartnerService partnerService;

    private final ReviewRepository reviewRepository;
    public ReviewServiceImpl(final UserService userService,
                             final PartnerService partnerService, ReviewRepository reviewRepository) {
        this.userService = userService;
        this.partnerService = partnerService;
        this.reviewRepository = reviewRepository;
    }

    @Override
    public User findUserByUuid(final String uuid) {
        return userService.findByUuid(uuid).get();
    }

    @Override
    public Partner findPartnerUuid(final String uuid) {
        return partnerService.findByUuid(uuid).get();
    }

    @Override
    public Review create(final Review review) {
        return reviewRepository.save(review);
    }
}
