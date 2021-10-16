package am.aca.bookingmanagement.facade.userfacade.userloginfacade;

import am.aca.bookingmanagement.facade.userfacade.userloginfacade.model.UserLoginRequestDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class UserLoginFacadeImpl implements UserLoginFacade {
    @Override
    public ResponseEntity<?> login(final UserLoginRequestDetails userLoginRequestDetails) {
        return ResponseEntity.ok("login");
    }
}