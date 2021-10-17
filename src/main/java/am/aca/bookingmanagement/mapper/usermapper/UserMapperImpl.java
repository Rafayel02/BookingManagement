package am.aca.bookingmanagement.mapper.usermapper;

import am.aca.bookingmanagement.entity.User;
import am.aca.bookingmanagement.facade.userfacade.userregisterdto.UserRegisterRequestDetails;
import am.aca.bookingmanagement.facade.userfacade.userregisterdto.UserRegisterResponseDetails;
import am.aca.bookingmanagement.service.userservice.dto.UserCreateDetails;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserCreateDetails mapRequestToDetails(final UserRegisterRequestDetails request) {
        final UserCreateDetails createdUser = new UserCreateDetails();
        createdUser.setFirstName(request.getFirstName());
        createdUser.setLastName(request.getLastName());
        createdUser.setEmail(request.getEmail());
        createdUser.setPassword(request.getPassword());
        return createdUser;
    }

    @Override
    public UserRegisterResponseDetails mapEntityToResponse(final User user) {
        final UserRegisterResponseDetails response = new UserRegisterResponseDetails();
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setEmail(user.getEmail());
        return response;
    }

    @Override
    public User mapCreteDetailsToEntity(final UserCreateDetails createDetails) {
        final User entity = new User();
        entity.setFirstName(createDetails.getFirstName());
        entity.setLastName(createDetails.getLastName());
        entity.setEmail(createDetails.getEmail());
        entity.setPassword(createDetails.getPassword());
        return entity;
    }

}