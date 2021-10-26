package am.aca.bookingmanagement.service.partnerservice;

import am.aca.bookingmanagement.entity.Partner;

import java.util.Optional;

public interface PartnerService {

    Partner create(Partner partner);

    Optional<Partner> findByEmail(String email);

    Optional<Partner> findById(Long id);

    void updateAverageRating(Long id);
}