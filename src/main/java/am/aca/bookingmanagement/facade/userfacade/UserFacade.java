package am.aca.bookingmanagement.facade.userfacade;

import am.aca.bookingmanagement.dto.userdto.UserLoginRequestDetails;
import am.aca.bookingmanagement.dto.userdto.UserLoginResponseDetails;
import am.aca.bookingmanagement.dto.userdto.UserRegisterRequestDetails;
import am.aca.bookingmanagement.dto.userdto.UserRegisterResponseDetails;

public interface UserFacade {
    UserRegisterResponseDetails register(UserRegisterRequestDetails userRegisterRequestDetails);

    UserLoginResponseDetails login(UserLoginRequestDetails userLoginRequestDetails);
}
