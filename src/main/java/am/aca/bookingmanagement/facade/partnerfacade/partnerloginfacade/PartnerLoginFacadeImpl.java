package am.aca.bookingmanagement.facade.partnerfacade.partnerloginfacade;

import am.aca.bookingmanagement.facade.partnerfacade.partnerloginfacade.model.PartnerLoginRequestDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class PartnerLoginFacadeImpl implements PartnerLoginFacade {

    @Override
    public ResponseEntity<?> login(final PartnerLoginRequestDetails partnerLoginRequestDetails) {
        return ResponseEntity.ok("login");
    }
}
