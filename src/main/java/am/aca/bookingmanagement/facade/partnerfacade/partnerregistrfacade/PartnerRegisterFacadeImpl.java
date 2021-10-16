package am.aca.bookingmanagement.facade.partnerfacade.partnerregistrfacade;

import am.aca.bookingmanagement.facade.partnerfacade.partnerregistrfacade.model.PartnerRegisterRequestDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class PartnerRegisterFacadeImpl implements PartnerRegisterFacade {
    @Override
    public ResponseEntity<?> register(final PartnerRegisterRequestDetails partnerRegisterRequestDetails) {
        return ResponseEntity.ok("register");
    }
}
