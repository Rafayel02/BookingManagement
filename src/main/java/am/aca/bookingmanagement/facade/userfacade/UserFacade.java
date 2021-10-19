package am.aca.bookingmanagement.facade.userfacade;

import am.aca.bookingmanagement.dto.userdto.login.UserLoginRequestDetails;
import am.aca.bookingmanagement.dto.userdto.login.UserLoginResponseDetails;
import am.aca.bookingmanagement.dto.userdto.register.UserRegisterRequestDetails;
import am.aca.bookingmanagement.dto.userdto.register.UserRegisterResponseDetails;

public interface UserFacade {
    UserRegisterResponseDetails register(UserRegisterRequestDetails userRegisterRequestDetails);

    UserLoginResponseDetails login(UserLoginRequestDetails userLoginRequestDetails);
}
