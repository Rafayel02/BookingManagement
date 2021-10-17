package am.aca.bookingmanagement.controller;

import am.aca.bookingmanagement.facade.partnerfacade.partnerlogindto.PartnerLoginRequestDetails;
import am.aca.bookingmanagement.facade.partnerfacade.partnerlogindto.PartnerLoginResponseDetails;
import am.aca.bookingmanagement.facade.partnerfacade.PartnerFacade;
import am.aca.bookingmanagement.facade.userfacade.userloginfacade.UserLoginFacade;
import am.aca.bookingmanagement.facade.userfacade.userloginfacade.model.UserLoginRequestDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    private final UserLoginFacade userLoginFacade;
    private final PartnerFacade partnerFacade;

    public LoginController(final UserLoginFacade userLoginFacade, final PartnerFacade partnerFacade) {
        this.userLoginFacade = userLoginFacade;
        this.partnerFacade = partnerFacade;
    }

    @PostMapping
    public ResponseEntity<?> loginUser(@RequestBody final UserLoginRequestDetails request) {
        return userLoginFacade.login(request);
    }

    @PostMapping("/partner")
    public ResponseEntity<?> loginPartner(@RequestBody final PartnerLoginRequestDetails request) {

        try {
            final PartnerLoginResponseDetails response = partnerFacade.login(request);
            return ResponseEntity.ok(response);
        } catch (final Exception e){
            return ResponseEntity.notFound().build();
        }
    }

}
