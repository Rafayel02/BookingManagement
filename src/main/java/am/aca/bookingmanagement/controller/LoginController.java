package am.aca.bookingmanagement.controller;

import am.aca.bookingmanagement.facade.partnerfacade.partnerloginfacade.PartnerLoginFacade;
import am.aca.bookingmanagement.facade.partnerfacade.partnerloginfacade.model.PartnerLoginRequestDetails;
import am.aca.bookingmanagement.facade.userfacade.userlogindto.UserLoginRequestDetails;
import am.aca.bookingmanagement.facade.userfacade.userlogindto.UserLoginResponseDetails;
import am.aca.bookingmanagement.facade.userfacade.UserFacade;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    private final PartnerLoginFacade partnerLoginFacade;
    private final UserFacade userFacade;

    public LoginController(PartnerLoginFacade partnerLoginFacade, final UserFacade userFacade) {
        this.userFacade = userFacade;
        this.partnerLoginFacade = partnerLoginFacade;
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
    public ResponseEntity<?> loginPartner(@RequestBody final PartnerLoginRequestDetails partnerLoginRequestDetails) {
        return partnerLoginFacade.login(partnerLoginRequestDetails);
    }

}
