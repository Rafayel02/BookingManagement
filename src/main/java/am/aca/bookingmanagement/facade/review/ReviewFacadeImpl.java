package am.aca.bookingmanagement.facade.review;

import org.springframework.stereotype.Component;
import am.aca.bookingmanagement.entity.Review;
import am.aca.bookingmanagement.facade.partner.PartnerFacade;
import am.aca.bookingmanagement.mapper.review.ReviewMapper;
import am.aca.bookingmanagement.service.review.ReviewService;
import am.aca.bookingmanagement.dto.review.ReviewRegisterRequestDetails;

@Component
public class ReviewFacadeImpl implements ReviewFacade {

    private final ReviewMapper reviewMapper;
    private final ReviewService reviewService;
    private final PartnerFacade partnerFacade;

    public ReviewFacadeImpl(final ReviewMapper reviewMapper,
                            final ReviewService reviewService,
                            final PartnerFacade partnerFacade) {
        this.reviewMapper = reviewMapper;
        this.reviewService = reviewService;
        this.partnerFacade = partnerFacade;
    }

    @Override
    public void registerReview(final ReviewRegisterRequestDetails request) {
        final Review review = reviewService.create(reviewMapper.mapRequestToEntity(request));
        final Long currentPartnerId = review.getPartner().getId();
        partnerFacade.updateAverageRating(currentPartnerId);
    }

}