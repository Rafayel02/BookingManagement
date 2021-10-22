package am.aca.bookingmanagement.facade.reviewfacade;

import am.aca.bookingmanagement.dto.reviewdto.ReviewRegisterRequestDetails;

public interface ReviewFacade {

    void registerReview(ReviewRegisterRequestDetails request);
}
