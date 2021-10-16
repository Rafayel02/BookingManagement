package am.aca.bookingmanagement.facade.userfacade.userloginfacade;

import am.aca.bookingmanagement.facade.userfacade.userloginfacade.model.UserLoginRequestDetails;
import org.springframework.http.ResponseEntity;

public interface UserLoginFacade {
    ResponseEntity<?> login(UserLoginRequestDetails userLoginRequestDetails);
}
