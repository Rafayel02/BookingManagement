package am.aca.bookingmanagement.controller;

import am.aca.bookingmanagement.domain.model.User;
import am.aca.bookingmanagement.facade.userfacade.userregisterfacade.UserRegisterFacade;
import am.aca.bookingmanagement.facade.userfacade.userregisterfacade.model.UserRegisterRequestDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
public class RegisterController {

    private final UserRegisterFacade userRegisterFacade;

    public RegisterController(UserRegisterFacade userRegistrationFacade) {
        this.userRegisterFacade = userRegistrationFacade;
    }

    @PostMapping
    public ResponseEntity<?> registerUser(@RequestBody final UserRegisterRequestDetails userRegisterRequestDetails) {
        return userRegisterFacade.register(userRegisterRequestDetails);
    }

    @PostMapping("/partner")
    public ResponseEntity<?> registerPartner(@RequestBody final User user) {
        return ResponseEntity.ok(user);
    }

}
