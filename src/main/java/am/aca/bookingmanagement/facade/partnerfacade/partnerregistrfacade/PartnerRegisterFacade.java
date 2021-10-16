package am.aca.bookingmanagement.facade.partnerfacade.partnerregistrfacade;

import am.aca.bookingmanagement.facade.partnerfacade.partnerregistrfacade.model.PartnerRegisterRequestDetails;
import org.springframework.http.ResponseEntity;

public interface PartnerRegisterFacade {
    ResponseEntity<?> register(PartnerRegisterRequestDetails partnerRegisterRequestDetails);
}
