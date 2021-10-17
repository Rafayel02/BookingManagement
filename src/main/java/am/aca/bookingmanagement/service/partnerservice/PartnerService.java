package am.aca.bookingmanagement.service.partnerservice;

import am.aca.bookingmanagement.entity.Partner;
import am.aca.bookingmanagement.service.partnerservice.dto.PartnerCreateDetails;

public interface PartnerService {

    Partner create(PartnerCreateDetails partnerCreateDetails);
}
