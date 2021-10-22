package am.aca.bookingmanagement.mapper.reviewmapper;

import am.aca.bookingmanagement.dto.reviewdto.ReviewRegisterRequestDetails;
import am.aca.bookingmanagement.entity.Partner;
import am.aca.bookingmanagement.entity.Review;
import am.aca.bookingmanagement.entity.User;

public interface ReviewMapper {
    Review mapRequestToEntity(Integer rating, String comment, User user, Partner partner);
}
