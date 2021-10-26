package am.aca.bookingmanagement.facade.userfacade;

import am.aca.bookingmanagement.dto.user.login.UserLoginRequestDetails;
import am.aca.bookingmanagement.dto.user.login.UserLoginResponseDetails;
import am.aca.bookingmanagement.dto.user.register.UserRegisterRequestDetails;
import am.aca.bookingmanagement.dto.user.register.UserRegisterResponseDetails;

public interface UserFacade {

    UserRegisterResponseDetails register(UserRegisterRequestDetails request);

    UserLoginResponseDetails login(UserLoginRequestDetails request);

}
