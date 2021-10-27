package am.aca.bookingmanagement.mapper.review;

import am.aca.bookingmanagement.entity.Review;
import am.aca.bookingmanagement.dto.review.ReviewRegisterRequestDetails;

public interface ReviewMapper {

    Review mapRequestToEntity(ReviewRegisterRequestDetails request);

}