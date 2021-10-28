package am.aca.bookingmanagement.service.partner;

import am.aca.bookingmanagement.entity.Partner;
import am.aca.bookingmanagement.entity.Review;

import java.util.List;
import java.util.Optional;

public interface PartnerService {

    Partner create(Partner partner);

    Optional<Partner> findByEmail(String email);

    Optional<Partner> findById(Long id);

    List<Review> getAllReviews(Long id);

    void updateAverageRating(Long id);

    void createPartnersCategories (Long partnerId, Integer categoryId);

    void createPartnersActivities(Long partnerId, Integer activityId);

}