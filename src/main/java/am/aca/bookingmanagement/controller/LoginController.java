package am.aca.bookingmanagement.controller;

import am.aca.bookingmanagement.facade.partnerfacade.PartnerFacade;
import am.aca.bookingmanagement.facade.partnerfacade.partnerlogindto.PartnerLoginRequestDetails;
import am.aca.bookingmanagement.facade.partnerfacade.partnerlogindto.PartnerLoginResponseDetails;
import am.aca.bookingmanagement.facade.userfacade.UserFacade;
import am.aca.bookingmanagement.facade.userfacade.userlogindto.UserLoginRequestDetails;
import am.aca.bookingmanagement.facade.userfacade.userlogindto.UserLoginResponseDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    private final UserFacade userFacade;
    private final PartnerFacade partnerFacade;

    public LoginController(final UserFacade userFacade, final PartnerFacade partnerFacade) {
        this.userFacade = userFacade;
        this.partnerFacade = partnerFacade;
    }

    @PostMapping
    public ResponseEntity<?> loginUser(@RequestBody final UserLoginRequestDetails userLoginRequestDetails) {
        try {
            final UserLoginResponseDetails response = userFacade.login(userLoginRequestDetails);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/partner")
    public ResponseEntity<?> loginPartner(@RequestBody final PartnerLoginRequestDetails request) {
        try {
            final PartnerLoginResponseDetails response = partnerFacade.login(request);
            return ResponseEntity.ok(response);
        } catch (final Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}