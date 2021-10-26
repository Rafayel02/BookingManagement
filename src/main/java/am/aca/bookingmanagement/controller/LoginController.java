package am.aca.bookingmanagement.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import am.aca.bookingmanagement.facade.partner.PartnerFacade;
import am.aca.bookingmanagement.facade.userfacade.UserFacade;
import am.aca.bookingmanagement.exception.UserNotFoundException;
import am.aca.bookingmanagement.exception.WrongPasswordException;
import am.aca.bookingmanagement.exception.SomethingWentWrongException;
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
        try {
            final UserLoginResponseDetails response = userFacade.login(request);
            return ResponseEntity.ok(response);
        } catch (final SomethingWentWrongException | UserNotFoundException | WrongPasswordException e) {
            e.printStackTrace();
            return new ResponseEntity<>("WRONG_EMAIL_OR_PASSWORD", HttpStatus.UNAUTHORIZED);
        } catch (final Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/partner")
    public ResponseEntity<?> loginPartner(@RequestBody final PartnerLoginRequestDetails request) {
        try {
            final PartnerLoginResponseDetails response = partnerFacade.login(request);
            return ResponseEntity.ok(response);
        } catch (final SomethingWentWrongException | UserNotFoundException | WrongPasswordException e) {
            return new ResponseEntity<>("WRONG_EMAIL_OR_PASSWORD", HttpStatus.UNAUTHORIZED);
        } catch (final Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}