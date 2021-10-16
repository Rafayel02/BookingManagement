package am.aca.bookingmanagement.controller;

import am.aca.bookingmanagement.facade.partnerfacade.partnerloginfacade.PartnerLoginFacade;
import am.aca.bookingmanagement.facade.partnerfacade.partnerloginfacade.model.PartnerLoginRequestDetails;
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
    private final PartnerLoginFacade partnerLoginFacade;

    public LoginController(UserLoginFacade userLoginFacade, PartnerLoginFacade partnerLoginFacade) {
        this.userLoginFacade = userLoginFacade;
        this.partnerLoginFacade = partnerLoginFacade;
    }

    @PostMapping
    public ResponseEntity<?> loginUser(@RequestBody final UserLoginRequestDetails userLoginRequestDetails) {
        return userLoginFacade.login(userLoginRequestDetails);
    }

    @PostMapping("/partner")
    public ResponseEntity<?> loginPartner(@RequestBody final PartnerLoginRequestDetails partnerLoginRequestDetails) {
        return partnerLoginFacade.login(partnerLoginRequestDetails);
    }

}
