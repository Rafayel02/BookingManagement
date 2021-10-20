package am.aca.bookingmanagement.facade.userfacade;

import am.aca.bookingmanagement.dto.userdto.login.UserLoginRequestDetails;
import am.aca.bookingmanagement.dto.userdto.login.UserLoginResponseDetails;
import am.aca.bookingmanagement.dto.userdto.register.UserRegisterRequestDetails;
import am.aca.bookingmanagement.dto.userdto.register.UserRegisterResponseDetails;
import am.aca.bookingmanagement.entity.User;
import am.aca.bookingmanagement.exception.UserNotFoundException;
import am.aca.bookingmanagement.exception.WrongPasswordException;
import am.aca.bookingmanagement.jwt.JwtTokenGenerator;
import am.aca.bookingmanagement.mapper.usermapper.UserMapper;
import am.aca.bookingmanagement.service.tokenservice.TokenService;
import am.aca.bookingmanagement.service.userservice.UserService;
import am.aca.bookingmanagement.service.userservice.UserServiceImpl;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserFacadeImpl implements UserFacade {

    private final UserService userService;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;
    private final JwtTokenGenerator jwtTokenGenerator;

    public UserFacadeImpl(final UserServiceImpl userServiceImpl,
                          final UserMapper userMapper,
                          final PasswordEncoder passwordEncoder, final TokenService tokenService, final JwtTokenGenerator jwtTokenGenerator) {
        this.userService = userServiceImpl;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
        this.jwtTokenGenerator = jwtTokenGenerator;
    }

    @Override
    public UserRegisterResponseDetails register(final UserRegisterRequestDetails request) {
        //TODO validation checks, if something doesn't match always throw SOMETHING_WENT_WRONG_EXCEPTION
        final User user = userService.create(userMapper.mapRegisterRequestToEntity(request));
//        final String token = tokenService.generateJWTToken(user);
//        System.out.println(jwtTokenGenerator.generate(user));
        final UserRegisterResponseDetails response = userMapper.mapEntityToRegisterResponse(user);
//        response.setToken(token);
        return response;
    }

    @Override
    public UserLoginResponseDetails login(final UserLoginRequestDetails request) {
        /*TODO getting token from request body, switching into
           token facade (to check token in db after some logic with token and restart it if needed)*/
        //TODO validation checks, if something doesn't match always throw SOMETHING_WENT_WRONG_EXCEPTION
        final Optional<User> byEmail = userService.findByEmail(request.getEmail());
        if(byEmail.isEmpty()) {
            throw new UserNotFoundException("USER_DOES_NOT_EXIST");
        }
        boolean passwordsMatch = passwordEncoder.matches(request.getPassword(), byEmail.get().getPassword());
        if (!passwordsMatch) {
            throw new WrongPasswordException("PASSWORDS_MISMATCH");
        }
        return userMapper.mapEntityToLoginResponse(byEmail.get());
    }

}