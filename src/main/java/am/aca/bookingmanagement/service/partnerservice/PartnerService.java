package am.aca.bookingmanagement.service.partnerservice;

import am.aca.bookingmanagement.dto.filterdto.FilterRequestDetails;
import am.aca.bookingmanagement.entity.Partner;

import java.util.List;
import java.util.Optional;

public interface PartnerService {

    Partner create(Partner partner);

    Optional<Partner> findByEmail(String email);

    Optional<Partner> findByUuid(String uuid);

    Long findIdByUuid(String uuid);

    Integer setPartnerRating(Integer rating, Long id);


}