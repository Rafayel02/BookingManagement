package am.aca.bookingmanagement.facade.partnerfacade;

import am.aca.bookingmanagement.dto.partnerdto.login.PartnerLoginRequestDetails;
import am.aca.bookingmanagement.dto.partnerdto.login.PartnerLoginResponseDetails;
import am.aca.bookingmanagement.dto.partnerdto.register.PartnerRegisterRequestDetails;
import am.aca.bookingmanagement.dto.partnerdto.register.PartnerRegisterResponseDetails;

public interface PartnerFacade {
    PartnerRegisterResponseDetails register(PartnerRegisterRequestDetails partnerRegisterRequestDetails);

    PartnerLoginResponseDetails login(PartnerLoginRequestDetails partnerLoginRequestDetails);
}
