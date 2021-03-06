package am.aca.bookingmanagement.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import am.aca.bookingmanagement.facade.user.UserFacade;
import am.aca.bookingmanagement.facade.partner.PartnerFacade;
import am.aca.bookingmanagement.dto.user.register.UserRegisterRequestDetails;
import am.aca.bookingmanagement.dto.user.register.UserRegisterResponseDetails;
import am.aca.bookingmanagement.dto.partner.register.PartnerRegisterRequestDetails;
import am.aca.bookingmanagement.dto.partner.register.PartnerRegisterResponseDetails;

@RestController
@RequestMapping("/register")
public class RegisterController {

    private final UserFacade userFacade;
    private final PartnerFacade partnerFacade;

    public RegisterController(final UserFacade userFacade,
                              final PartnerFacade partnerFacade) {
        this.userFacade = userFacade;
        this.partnerFacade = partnerFacade;
    }

    @PostMapping
    public ResponseEntity<?> registerUser(@RequestBody final UserRegisterRequestDetails request) {
        final UserRegisterResponseDetails response = userFacade.register(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/partner")
    public ResponseEntity<?> registerPartner(@RequestBody final PartnerRegisterRequestDetails request) {
        final PartnerRegisterResponseDetails response = partnerFacade.register(request);
        return ResponseEntity.ok(response);
    }

}