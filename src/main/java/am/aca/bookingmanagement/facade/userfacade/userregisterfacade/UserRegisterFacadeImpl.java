package am.aca.bookingmanagement.facade.userfacade.userregisterfacade;

import am.aca.bookingmanagement.facade.userfacade.userregisterfacade.model.UserRegisterRequestDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class UserRegisterFacadeImpl implements UserRegisterFacade {

    @Override
    public ResponseEntity<?> register(final UserRegisterRequestDetails userRegisterRequestDetails) {
        return ResponseEntity.ok("register");
    }

}
