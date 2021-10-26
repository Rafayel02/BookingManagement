package am.aca.bookingmanagement.facade.partner;

import am.aca.bookingmanagement.dto.partner.login.PartnerLoginRequestDetails;
import am.aca.bookingmanagement.dto.partner.login.PartnerLoginResponseDetails;
import am.aca.bookingmanagement.dto.partner.register.PartnerRegisterRequestDetails;
import am.aca.bookingmanagement.dto.partner.register.PartnerRegisterResponseDetails;

public interface PartnerFacade {

    PartnerRegisterResponseDetails register(PartnerRegisterRequestDetails request);

    PartnerLoginResponseDetails login(PartnerLoginRequestDetails request);

    void updateAverageRating(Long currentPartnerId);

}