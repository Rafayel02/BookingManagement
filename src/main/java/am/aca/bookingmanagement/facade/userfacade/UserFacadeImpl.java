package am.aca.bookingmanagement.facade.userfacade;

import am.aca.bookingmanagement.entity.User;
import am.aca.bookingmanagement.facade.userfacade.userlogindto.UserLoginRequestDetails;
import am.aca.bookingmanagement.facade.userfacade.userlogindto.UserLoginResponseDetails;
import am.aca.bookingmanagement.facade.userfacade.userregisterdto.UserRegisterRequestDetails;
import am.aca.bookingmanagement.facade.userfacade.userregisterdto.UserRegisterResponseDetails;
import am.aca.bookingmanagement.mapper.usermapper.UserMapper;
import am.aca.bookingmanagement.service.userservice.UserService;
import am.aca.bookingmanagement.service.userservice.UserServiceImpl;
import org.springframework.stereotype.Component;

@Component
public class UserFacadeImpl implements UserFacade {

    private final UserService userService;
    private final UserMapper userMapper;

    public UserFacadeImpl(final UserServiceImpl userServiceImpl, final UserMapper userMapper) {
        this.userService = userServiceImpl;
        this.userMapper = userMapper;
    }

    @Override
    public UserRegisterResponseDetails register(final UserRegisterRequestDetails request) {
        final User user = userService.create(userMapper.mapRequestToDetails(request));
        /*TODO switching to token facade, to generate token and save in db
            (rather to do with transactions of saving user and token)*/
        return userMapper.mapEntityToResponse(user);
    }

    @Override
    public UserLoginResponseDetails login(final UserLoginRequestDetails request) {
        /*TODO getting token from request body, switching into
           token facade (to check token in db after some logic with token and restart it if needed)*/
        return null;
    }

}