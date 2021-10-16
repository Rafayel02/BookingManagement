package am.aca.bookingmanagement.controller;

import am.aca.bookingmanagement.domain.model.User;
import am.aca.bookingmanagement.facade.partnerfacade.partnerregistrfacade.PartnerRegisterFacade;
import am.aca.bookingmanagement.facade.partnerfacade.partnerregistrfacade.model.PartnerRegisterRequestDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/register")
public class RegisterController {

    private final PartnerRegisterFacade partnerRegisterFacade;

    public RegisterController(final PartnerRegisterFacade partnerRegisterFacade) {
        this.partnerRegisterFacade = partnerRegisterFacade;
    }

    @PostMapping("/")
    public ResponseEntity<?> registerUser(@RequestBody final User user) {
        return ResponseEntity.ok(user);
    }

    @PostMapping("/partner")
    public ResponseEntity<?> registerPartner(@RequestBody final PartnerRegisterRequestDetails partnerRegisterRequestDetails) {

        return partnerRegisterFacade.register(partnerRegisterRequestDetails);
    }
}
