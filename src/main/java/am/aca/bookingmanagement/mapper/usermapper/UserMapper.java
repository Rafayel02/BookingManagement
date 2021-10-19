package am.aca.bookingmanagement.mapper.usermapper;

import am.aca.bookingmanagement.dto.userdto.UserLoginResponseDetails;
import am.aca.bookingmanagement.dto.userdto.UserRegisterRequestDetails;
import am.aca.bookingmanagement.dto.userdto.UserRegisterResponseDetails;
import am.aca.bookingmanagement.entity.User;

public interface UserMapper {

    UserRegisterResponseDetails mapEntityToRegisterResponse(am.aca.bookingmanagement.entity.User user);

    User mapRegisterRequestToEntity(UserRegisterRequestDetails request);

    UserLoginResponseDetails mapEntityToLoginResponse(User user);
}