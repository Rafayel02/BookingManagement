package am.aca.bookingmanagement.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import am.aca.bookingmanagement.facade.user.UserFacade;
import am.aca.bookingmanagement.facade.partner.PartnerFacade;
import am.aca.bookingmanagement.dto.user.login.UserLoginRequestDetails;
import am.aca.bookingmanagement.dto.user.login.UserLoginResponseDetails;
import am.aca.bookingmanagement.dto.partner.login.PartnerLoginRequestDetails;
import am.aca.bookingmanagement.dto.partner.login.PartnerLoginResponseDetails;

@RestController
@RequestMapping("/login")
public class LoginController {

    private final UserFacade userFacade;
    private final PartnerFacade partnerFacade;

    public LoginController(final UserFacade userFacade,
                           final PartnerFacade partnerFacade) {
        this.userFacade = userFacade;
        this.partnerFacade = partnerFacade;
    }

    @PostMapping
    public ResponseEntity<?> loginUser(@RequestBody final UserLoginRequestDetails request) {
        final UserLoginResponseDetails response = userFacade.login(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/partner")
    public ResponseEntity<?> loginPartner(@RequestBody final PartnerLoginRequestDetails request) {
        final PartnerLoginResponseDetails response = partnerFacade.login(request);
        return ResponseEntity.ok(response);
    }

}