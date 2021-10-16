package am.aca.bookingmanagement.controller;

import am.aca.bookingmanagement.facade.userfacade.userregisterfacade.UserRegisterFacade;
import am.aca.bookingmanagement.facade.userfacade.userregisterfacade.model.UserRegisterRequestDetails;
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

    private final UserRegisterFacade userRegisterFacade;
    private final PartnerRegisterFacade partnerRegisterFacade;

    public RegisterController(UserRegisterFacade userRegistrationFacade, PartnerRegisterFacade partnerRegisterFacade) {
        this.userRegisterFacade = userRegistrationFacade;
        this.partnerRegisterFacade = partnerRegisterFacade;
    }

    @PostMapping
    public ResponseEntity<?> registerUser(@RequestBody final UserRegisterRequestDetails userRegisterRequestDetails) {
        return userRegisterFacade.register(userRegisterRequestDetails);
    }
    @PostMapping("/partner")
    public ResponseEntity<?> registerPartner(@RequestBody final PartnerRegisterRequestDetails partnerRegisterRequestDetails) {

        return partnerRegisterFacade.register(partnerRegisterRequestDetails);
    }
}
