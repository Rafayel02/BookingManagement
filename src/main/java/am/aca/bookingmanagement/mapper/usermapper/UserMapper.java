package am.aca.bookingmanagement.mapper.usermapper;

import am.aca.bookingmanagement.entity.User;
import am.aca.bookingmanagement.facade.userfacade.userregisterdto.UserRegisterRequestDetails;
import am.aca.bookingmanagement.facade.userfacade.userregisterdto.UserRegisterResponseDetails;
import am.aca.bookingmanagement.service.userservice.dto.UserCreateDetails;

public interface UserMapper {

    UserCreateDetails mapRequestToDetails(UserRegisterRequestDetails registerRequestDetails);

    UserRegisterResponseDetails mapEntityToResponse(User user);

    User mapCreteDetailsToEntity(UserCreateDetails userCreateDetails);

}