package am.aca.bookingmanagement.facade.partnerfacade;

import am.aca.bookingmanagement.dto.partnerdto.PartnerLoginRequestDetails;
import am.aca.bookingmanagement.dto.partnerdto.PartnerLoginResponseDetails;
import am.aca.bookingmanagement.dto.partnerdto.PartnerRegisterRequestDetails;
import am.aca.bookingmanagement.dto.partnerdto.PartnerRegisterResponseDetails;

public interface PartnerFacade {
    PartnerRegisterResponseDetails register(PartnerRegisterRequestDetails partnerRegisterRequestDetails);

    PartnerLoginResponseDetails login(PartnerLoginRequestDetails partnerLoginRequestDetails);
}
