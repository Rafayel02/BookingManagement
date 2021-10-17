package am.aca.bookingmanagement.service.partnerservice;

import am.aca.bookingmanagement.entity.Restaurant;
import am.aca.bookingmanagement.service.partnerservice.dto.PartnerCreateDetails;

public interface PartnerService {

    Restaurant create(PartnerCreateDetails partnerCreateDetails);
}
