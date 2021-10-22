package am.aca.bookingmanagement.service.reviewservice;

import am.aca.bookingmanagement.entity.Partner;
import am.aca.bookingmanagement.entity.Review;
import am.aca.bookingmanagement.entity.User;

public interface ReviewService {

    User findUserByUuid(String uuid);

    Partner findPartnerUuid(String uuid);

    Review create(Review review);
}
