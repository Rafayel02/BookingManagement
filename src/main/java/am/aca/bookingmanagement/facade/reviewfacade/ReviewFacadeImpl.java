package am.aca.bookingmanagement.facade.reviewfacade;

import am.aca.bookingmanagement.dto.reviewdto.ReviewRegisterRequestDetails;
import am.aca.bookingmanagement.entity.Review;
import am.aca.bookingmanagement.facade.partnerfacade.PartnerFacade;
import am.aca.bookingmanagement.mapper.reviewmapper.ReviewMapper;
import am.aca.bookingmanagement.service.reviewservice.ReviewService;
import org.springframework.stereotype.Component;

@Component
public class ReviewFacadeImpl implements ReviewFacade {

    private final ReviewService reviewService;
    private final ReviewMapper reviewMapper;
    private final PartnerFacade partnerFacade;

    public ReviewFacadeImpl(final ReviewService reviewService,
                            final ReviewMapper reviewMapper,
                            final PartnerFacade partnerFacade) {
        this.reviewService = reviewService;
        this.reviewMapper = reviewMapper;
        this.partnerFacade = partnerFacade;
    }

    @Override
    public void registerReview(final ReviewRegisterRequestDetails request) {
        final Review review = reviewService.create(reviewMapper.mapRequestToEntity(request));
        final Long currentPartnerId = review.getPartner().getId();
        partnerFacade.updateAverageRating(currentPartnerId);
    }

}