package am.aca.bookingmanagement.controller;

import am.aca.bookingmanagement.facade.partnerfacade.partnerloginfacade.PartnerLoginFacade;
import am.aca.bookingmanagement.facade.partnerfacade.partnerloginfacade.model.PartnerLoginRequestDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    private final PartnerLoginFacade partnerLoginFacade;

    public LoginController(final PartnerLoginFacade partnerLoginFacade) {
        this.partnerLoginFacade = partnerLoginFacade;
    }

    @PostMapping("/partner")
    public ResponseEntity<?> loginPartner(@RequestBody final PartnerLoginRequestDetails partnerLoginRequestDetails) {
        return partnerLoginFacade.login(partnerLoginRequestDetails);
    }
}
