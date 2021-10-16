package am.aca.bookingmanagement.facade.partnerfacade.partnerloginfacade;

import am.aca.bookingmanagement.facade.partnerfacade.partnerloginfacade.model.PartnerLoginRequestDetails;
import org.springframework.http.ResponseEntity;

public interface PartnerLoginFacade {
    ResponseEntity<?> login(PartnerLoginRequestDetails partnerLoginRequestDetails);
}
