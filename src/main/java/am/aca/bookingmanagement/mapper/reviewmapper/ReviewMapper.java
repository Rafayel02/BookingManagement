package am.aca.bookingmanagement.mapper.reviewmapper;

import am.aca.bookingmanagement.entity.Review;
import am.aca.bookingmanagement.dto.review.ReviewRegisterRequestDetails;

public interface ReviewMapper {

    Review mapRequestToEntity(ReviewRegisterRequestDetails request);

}