package am.aca.bookingmanagement.mapper.user;

import am.aca.bookingmanagement.entity.User;
import am.aca.bookingmanagement.dto.user.login.UserLoginResponseDetails;
import am.aca.bookingmanagement.dto.user.register.UserRegisterRequestDetails;
import am.aca.bookingmanagement.dto.user.register.UserRegisterResponseDetails;

public interface UserMapper {

    UserRegisterResponseDetails mapEntityToRegisterResponse(User user);

    User mapRegisterRequestToEntity(UserRegisterRequestDetails request);

    UserLoginResponseDetails mapEntityToLoginResponse(User user);

}