package am.aca.bookingmanagement.controller;

import am.aca.bookingmanagement.facade.userfacade.userloginfacade.UserLoginFacade;
import am.aca.bookingmanagement.facade.userfacade.userloginfacade.model.UserLoginRequestDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class LoginController {

    private final UserLoginFacade userLoginFacade;

    public LoginController(UserLoginFacade userLoginFacade) {
        this.userLoginFacade = userLoginFacade;
    }

    @PostMapping
    public ResponseEntity<?> loginUser(@RequestBody final UserLoginRequestDetails userLoginRequestDetails) {
        return userLoginFacade.login(userLoginRequestDetails);
    }

}
