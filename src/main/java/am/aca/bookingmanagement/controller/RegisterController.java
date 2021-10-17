package am.aca.bookingmanagement.controller;

import am.aca.bookingmanagement.facade.partnerfacade.partnerregisterdto.PartnerRegisterResponseDetails;
import am.aca.bookingmanagement.facade.userfacade.userregisterfacade.UserRegisterFacade;
import am.aca.bookingmanagement.facade.userfacade.userregisterfacade.model.UserRegisterRequestDetails;
import am.aca.bookingmanagement.facade.partnerfacade.PartnerFacade;
import am.aca.bookingmanagement.facade.partnerfacade.partnerregisterdto.PartnerRegisterRequestDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
public class RegisterController {

    private final UserRegisterFacade userRegisterFacade;
    private final PartnerFacade partnerFacade;

    public RegisterController(UserRegisterFacade userRegistrationFacade, PartnerFacade partnerFacade) {
        this.userRegisterFacade = userRegistrationFacade;
        this.partnerFacade = partnerFacade;
    }

    @PostMapping
    public ResponseEntity<?> registerUser(@RequestBody final UserRegisterRequestDetails userRegisterRequestDetails) {
        return userRegisterFacade.register(userRegisterRequestDetails);
    }

    @PostMapping("/partner")
    public ResponseEntity<?> registerPartner(@RequestBody final PartnerRegisterRequestDetails request) {

        try{
            final PartnerRegisterResponseDetails response = partnerFacade.register(request);
            return ResponseEntity.ok(response);
        } catch (final Exception e){
            return ResponseEntity.notFound().build();
        }

    }
}
