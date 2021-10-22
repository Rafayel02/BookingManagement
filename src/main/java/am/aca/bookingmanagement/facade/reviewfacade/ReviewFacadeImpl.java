package am.aca.bookingmanagement.facade.reviewfacade;

import am.aca.bookingmanagement.dto.reviewdto.ReviewRegisterRequestDetails;
import am.aca.bookingmanagement.entity.Partner;
import am.aca.bookingmanagement.entity.User;
import am.aca.bookingmanagement.mapper.reviewmapper.ReviewMapper;
import am.aca.bookingmanagement.service.reviewservice.ReviewService;
import org.springframework.stereotype.Component;

@Component
public class ReviewFacadeImpl implements ReviewFacade {

    private final ReviewService reviewService;
    private final ReviewMapper reviewMapper;

    public ReviewFacadeImpl(final ReviewService reviewService,
                            final ReviewMapper reviewMapper) {
        this.reviewService = reviewService;
        this.reviewMapper = reviewMapper;
    }

    @Override
    public void registerReview(final ReviewRegisterRequestDetails request) {
        final User userByUuid = reviewService.findUserByUuid(request.getUserUuid());
        final Partner partnerUuid = reviewService.findPartnerUuid(request.getPartnerUuid());

        reviewService.create(reviewMapper.mapRequestToEntity(request.getRating(),
                request.getComment(), userByUuid, partnerUuid));
    }

}
