package am.aca.bookingmanagement.facade.partnerfacade;

import am.aca.bookingmanagement.facade.partnerfacade.partnerlogindto.PartnerLoginRequestDetails;
import am.aca.bookingmanagement.facade.partnerfacade.partnerlogindto.PartnerLoginResponseDetails;
import am.aca.bookingmanagement.facade.partnerfacade.partnerregisterdto.PartnerRegisterRequestDetails;
import am.aca.bookingmanagement.facade.partnerfacade.partnerregisterdto.PartnerRegisterResponseDetails;

public interface PartnerFacade {
    PartnerRegisterResponseDetails register(PartnerRegisterRequestDetails partnerRegisterRequestDetails);

    PartnerLoginResponseDetails login(PartnerLoginRequestDetails partnerLoginRequestDetails);
}
