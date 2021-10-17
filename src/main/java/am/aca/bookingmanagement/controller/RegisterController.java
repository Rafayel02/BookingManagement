package am.aca.bookingmanagement.controller;

import am.aca.bookingmanagement.facade.userfacade.UserFacade;
import am.aca.bookingmanagement.facade.userfacade.userregisterdto.UserRegisterRequestDetails;
import am.aca.bookingmanagement.facade.partnerfacade.partnerregistrfacade.PartnerRegisterFacade;
import am.aca.bookingmanagement.facade.partnerfacade.partnerregistrfacade.model.PartnerRegisterRequestDetails;
import am.aca.bookingmanagement.facade.userfacade.userregisterdto.UserRegisterResponseDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
public class RegisterController {

    private final UserFacade userRegisterFacade;
    private final PartnerRegisterFacade partnerRegisterFacade;

    public RegisterController(UserFacade userRegistrationFacade, PartnerRegisterFacade partnerRegisterFacade) {
        this.userRegisterFacade = userRegistrationFacade;
        this.partnerRegisterFacade = partnerRegisterFacade;
    }

    @PostMapping
    public ResponseEntity<?> registerUser(@RequestBody final UserRegisterRequestDetails request) {
        try {
            UserRegisterResponseDetails response = userRegisterFacade.register(request);
            return ResponseEntity.ok(response);
        } catch (final Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/partner")
    public ResponseEntity<?> registerPartner(@RequestBody final PartnerRegisterRequestDetails partnerRegisterRequestDetails) {

        return partnerRegisterFacade.register(partnerRegisterRequestDetails);
    }
}
