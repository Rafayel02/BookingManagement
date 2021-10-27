package am.aca.bookingmanagement.service.partner;

import am.aca.bookingmanagement.dto.filterdto.FilterRequestDetails;
import am.aca.bookingmanagement.entity.Partner;

import java.util.List;
import java.util.Optional;

public interface PartnerService {

    Partner create(Partner partner);

    Optional<Partner> findByEmail(String email);

    Optional<Partner> findById(Long id);

    void updateAverageRating(Long id);


}