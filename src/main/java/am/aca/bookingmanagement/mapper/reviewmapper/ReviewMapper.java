package am.aca.bookingmanagement.mapper.reviewmapper;

import am.aca.bookingmanagement.dto.reviewdto.ReviewRegisterRequestDetails;
import am.aca.bookingmanagement.entity.Review;

public interface ReviewMapper {
    Review mapRequestToEntity(ReviewRegisterRequestDetails request);
}
