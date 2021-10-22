package am.aca.bookingmanagement.mapper.reviewmapper;

import am.aca.bookingmanagement.dto.reviewdto.ReviewRegisterRequestDetails;
import am.aca.bookingmanagement.entity.Partner;
import am.aca.bookingmanagement.entity.Review;
import am.aca.bookingmanagement.entity.User;
import org.springframework.stereotype.Component;

@Component
public class ReviewMapperImpl implements ReviewMapper {


    @Override
    public Review mapRequestToEntity(Integer rating, String comment, User user, Partner partner) {
        Review review = new Review();
        review.setRating(rating);
        review.setComment(comment);
        review.setUser(user);
        review.setPartner(partner);
        return review;
    }
}
