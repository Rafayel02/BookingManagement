package am.aca.bookingmanagement.controller;

import am.aca.bookingmanagement.facade.partnerfacade.PartnerFacade;
import am.aca.bookingmanagement.facade.partnerfacade.partnerregisterdto.PartnerRegisterRequestDetails;
import am.aca.bookingmanagement.facade.partnerfacade.partnerregisterdto.PartnerRegisterResponseDetails;
import am.aca.bookingmanagement.facade.userfacade.UserFacade;
import am.aca.bookingmanagement.facade.userfacade.userregisterdto.UserRegisterRequestDetails;
import am.aca.bookingmanagement.facade.userfacade.userregisterdto.UserRegisterResponseDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/register")
public class RegisterController {

    private final UserFacade userFacade;
    private final PartnerFacade partnerFacade;

    public RegisterController(final UserFacade userFacade, final PartnerFacade partnerFacade) {
        this.userFacade = userFacade;
        this.partnerFacade = partnerFacade;
    }

    @PostMapping
    public ResponseEntity<?> registerUser(@RequestBody final UserRegisterRequestDetails request) {
        try {
            final UserRegisterResponseDetails response = userFacade.register(request);
            return ResponseEntity.ok(response);
        } catch (final Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/partner")
    public ResponseEntity<?> registerPartner(@RequestBody final PartnerRegisterRequestDetails request) {
        try {
            final PartnerRegisterResponseDetails response = partnerFacade.register(request);
            return ResponseEntity.ok(response);
        } catch (final Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}
