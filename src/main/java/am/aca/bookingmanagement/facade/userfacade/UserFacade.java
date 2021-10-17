package am.aca.bookingmanagement.facade.userfacade;

import am.aca.bookingmanagement.facade.userfacade.userlogindto.UserLoginRequestDetails;
import am.aca.bookingmanagement.facade.userfacade.userlogindto.UserLoginResponseDetails;
import am.aca.bookingmanagement.facade.userfacade.userregisterdto.UserRegisterRequestDetails;
import am.aca.bookingmanagement.facade.userfacade.userregisterdto.UserRegisterResponseDetails;

public interface UserFacade {
    UserRegisterResponseDetails register(UserRegisterRequestDetails userRegisterRequestDetails);

    UserLoginResponseDetails login(UserLoginRequestDetails userLoginRequestDetails);
}
