package am.aca.bookingmanagement.facade.userfacade;

import org.springframework.stereotype.Component;
import am.aca.bookingmanagement.entity.User;
import am.aca.bookingmanagement.checker.ValidationChecker;
import am.aca.bookingmanagement.mapper.usermapper.UserMapper;
import am.aca.bookingmanagement.exception.UserNotFoundException;
import am.aca.bookingmanagement.service.userservice.UserService;
import am.aca.bookingmanagement.exception.WrongPasswordException;
import org.springframework.security.crypto.password.PasswordEncoder;
import am.aca.bookingmanagement.exception.SomethingWentWrongException;
import am.aca.bookingmanagement.dto.user.login.UserLoginRequestDetails;
import am.aca.bookingmanagement.dto.user.login.UserLoginResponseDetails;
import am.aca.bookingmanagement.dto.user.register.UserRegisterRequestDetails;
import am.aca.bookingmanagement.dto.user.register.UserRegisterResponseDetails;

import java.util.Optional;

@Component
public class UserFacadeImpl implements UserFacade {

    private final UserMapper userMapper;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final ValidationChecker validationChecker;

    public UserFacadeImpl(final UserMapper userMapper,
                          final UserService userService,
                          final PasswordEncoder passwordEncoder,
                          final ValidationChecker validationChecker) {
        this.userMapper = userMapper;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.validationChecker = validationChecker;
    }

    @Override
    public UserRegisterResponseDetails register(final UserRegisterRequestDetails request) {
        if (!validationChecker.isUserRegistrationValid(request.getFirstName(), request.getLastName(),
                request.getEmail(), request.getPassword())) {
            throw new SomethingWentWrongException("INVALID_INPUT");
        }
        final User user = userService.create(userMapper.mapRegisterRequestToEntity(request));
        return userMapper.mapEntityToRegisterResponse(user);
    }

    @Override
    public UserLoginResponseDetails login(final UserLoginRequestDetails request) {
        if (!validationChecker.isLoginValid(request.getEmail(), request.getPassword())) {
            throw new SomethingWentWrongException("INVALID_EMAIL_OR_PASSWORD");
        }
        final Optional<User> user = userService.findByEmail(request.getEmail());
        if (user.isEmpty()) {
            throw new UserNotFoundException("USER_DOES_NOT_EXIST");
        }
        final boolean doPasswordsMatch = passwordEncoder.matches(request.getPassword(), user.get().getPassword());
        if (!doPasswordsMatch) {
            throw new WrongPasswordException("PASSWORDS_MISMATCH");
        }
        return userMapper.mapEntityToLoginResponse(user.get());
    }

}