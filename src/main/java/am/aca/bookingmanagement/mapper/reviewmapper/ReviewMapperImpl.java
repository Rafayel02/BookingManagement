package am.aca.bookingmanagement.mapper.reviewmapper;

import am.aca.bookingmanagement.dto.reviewdto.ReviewRegisterRequestDetails;
import am.aca.bookingmanagement.entity.Partner;
import am.aca.bookingmanagement.entity.Review;
import am.aca.bookingmanagement.entity.User;
import org.springframework.stereotype.Component;

@Component
public class ReviewMapperImpl implements ReviewMapper {


    @Override
    public Review mapRequestToEntity(final ReviewRegisterRequestDetails request) {
        final Review review = new Review();
        final User user = new User();
        final Partner partner = new Partner();
        user.setId(request.getUserId());
        partner.setId(request.getPartnerId());
        review.setUser(user);
        review.setPartner(partner);
        review.setRating(request.getRating());
        review.setComment(request.getComment());
        return review;
    }
}
