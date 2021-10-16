package am.aca.bookingmanagement.facade.userfacade.userregisterfacade;

import am.aca.bookingmanagement.facade.userfacade.userregisterfacade.model.UserRegisterRequestDetails;
import org.springframework.http.ResponseEntity;

public interface UserRegisterFacade {
    ResponseEntity<?> register(UserRegisterRequestDetails userRegisterRequestDetails);
}
