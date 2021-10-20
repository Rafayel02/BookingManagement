package am.aca.bookingmanagement.controller;

import am.aca.bookingmanagement.dto.partnerdto.register.PartnerRegisterRequestDetails;
import am.aca.bookingmanagement.dto.partnerdto.register.PartnerRegisterResponseDetails;
import am.aca.bookingmanagement.dto.userdto.register.UserRegisterRequestDetails;
import am.aca.bookingmanagement.dto.userdto.register.UserRegisterResponseDetails;
import am.aca.bookingmanagement.exception.SomethingWentWrongException;
import am.aca.bookingmanagement.exception.UserAlreadyExistsException;
import am.aca.bookingmanagement.facade.partnerfacade.PartnerFacade;
import am.aca.bookingmanagement.facade.userfacade.UserFacade;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        }catch(final SomethingWentWrongException e){
            return new ResponseEntity<>("PROVIDED_EMAIL_OR_PASSWORD_IS_INVALID", HttpStatus.FORBIDDEN);
        }
        catch (final UserAlreadyExistsException e) {
            return new ResponseEntity<>("PROVIDED_EMAIL_IS_ALREADY_REGISTERED", HttpStatus.FORBIDDEN);
        }
        catch (final Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/partner")
    public ResponseEntity<?> registerPartner(@RequestBody final PartnerRegisterRequestDetails request) {
        try {
            final PartnerRegisterResponseDetails response = partnerFacade.register(request);
            return ResponseEntity.ok(response);
        } catch(final SomethingWentWrongException e){
            return new ResponseEntity<>("PROVIDED_EMAIL_OR_PASSWORD_IS_INVALID", HttpStatus.FORBIDDEN);
        }
        catch (final UserAlreadyExistsException e) {
            return new ResponseEntity<>("PROVIDED_EMAIL_IS_ALREADY_REGISTERED", HttpStatus.FORBIDDEN);
        }
        catch (final Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}
