package am.aca.bookingmanagement.facade.userfacade;

import am.aca.bookingmanagement.checker.ValidationChecker;
import am.aca.bookingmanagement.dto.userdto.login.UserLoginRequestDetails;
import am.aca.bookingmanagement.dto.userdto.login.UserLoginResponseDetails;
import am.aca.bookingmanagement.dto.userdto.register.UserRegisterRequestDetails;
import am.aca.bookingmanagement.dto.userdto.register.UserRegisterResponseDetails;
import am.aca.bookingmanagement.entity.User;
import am.aca.bookingmanagement.exception.UserNotFoundException;
import am.aca.bookingmanagement.exception.SomethingWentWrongException;
import am.aca.bookingmanagement.exception.WrongPasswordException;
import am.aca.bookingmanagement.jwt.JwtTokenGenerator;
import am.aca.bookingmanagement.mapper.usermapper.UserMapper;
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
    private final ValidationChecker validationChecker;
    private final JwtTokenGenerator jwtTokenGenerator;

    public UserFacadeImpl(final UserServiceImpl userServiceImpl,
                          final UserMapper userMapper,
                          final PasswordEncoder passwordEncoder,
                          final ValidationChecker validationChecker,
                          final JwtTokenGenerator jwtTokenGenerator) {
        this.userService = userServiceImpl;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.validationChecker = validationChecker;
        this.jwtTokenGenerator = jwtTokenGenerator;
    }

    @Override
    public UserRegisterResponseDetails register(final UserRegisterRequestDetails request) {
        if (!validationChecker.isEmailValid(request.getEmail())) {
            throw new SomethingWentWrongException("Invalid email address");
        }
        if (!validationChecker.isPasswordValid(request.getPassword())) {
            throw new SomethingWentWrongException("Invalid password format");
        }
        final User user = userService.create(userMapper.mapRegisterRequestToEntity(request));
        return userMapper.mapEntityToRegisterResponse(user);
    }

    @Override
    public UserLoginResponseDetails login(final UserLoginRequestDetails request) {
        if (!validationChecker.isEmailValid(request.getEmail())) {
            throw new SomethingWentWrongException("Invalid email address");
        }
        if (!validationChecker.isPasswordValid(request.getPassword())) {
            throw new SomethingWentWrongException("Invalid password format");
        }
        final Optional<User> byEmail = userService.findByEmail(request.getEmail());
        if (byEmail.isEmpty()) {
            throw new UserNotFoundException("USER_DOES_NOT_EXIST");
        }
        final boolean passwordsMatch = passwordEncoder.matches(request.getPassword(), byEmail.get().getPassword());
        if (!passwordsMatch) {
            throw new WrongPasswordException("PASSWORDS_MISMATCH");
        }
        return userMapper.mapEntityToLoginResponse(byEmail.get());
    }

}