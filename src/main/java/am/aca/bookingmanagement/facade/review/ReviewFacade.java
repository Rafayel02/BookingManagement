package am.aca.bookingmanagement.facade.review;

import am.aca.bookingmanagement.dto.review.ReviewRegisterRequestDetails;

public interface ReviewFacade {

    void registerReview(ReviewRegisterRequestDetails request);

}