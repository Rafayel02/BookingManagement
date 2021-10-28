package am.aca.bookingmanagement.facade.user;

import am.aca.bookingmanagement.dto.filter.FilterUserResponseDetails;
import am.aca.bookingmanagement.entity.Review;
import org.springframework.stereotype.Component;
import am.aca.bookingmanagement.entity.User;
import am.aca.bookingmanagement.checker.ValidationChecker;
import am.aca.bookingmanagement.mapper.user.UserMapper;
import am.aca.bookingmanagement.exception.UserNotFoundException;
import am.aca.bookingmanagement.service.user.UserService;
import am.aca.bookingmanagement.exception.WrongPasswordException;
import org.springframework.security.crypto.password.PasswordEncoder;
import am.aca.bookingmanagement.exception.SomethingWentWrongException;
import am.aca.bookingmanagement.dto.user.login.UserLoginRequestDetails;
import am.aca.bookingmanagement.dto.user.login.UserLoginResponseDetails;
import am.aca.bookingmanagement.dto.user.register.UserRegisterRequestDetails;
import am.aca.bookingmanagement.dto.user.register.UserRegisterResponseDetails;

import java.util.List;
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
            throw new SomethingWentWrongException();
        }
        final User user = userService.create(userMapper.mapRegisterRequestToEntity(request));
        return userMapper.mapEntityToRegisterResponse(user);
    }

    @Override
    public UserLoginResponseDetails login(final UserLoginRequestDetails request) {
        if (!validationChecker.isLoginValid(request.getEmail(), request.getPassword())) {
            throw new SomethingWentWrongException();
        }
        final Optional<User> user = userService.findByEmail(request.getEmail());
        if (user.isEmpty()) {
            throw new UserNotFoundException();
        }
        final boolean doPasswordsMatch = passwordEncoder.matches(request.getPassword(), user.get().getPassword());
        if (!doPasswordsMatch) {
            throw new WrongPasswordException();
        }
        return userMapper.mapEntityToLoginResponse(user.get());
    }

    @Override
    public List<Review> getAllReviews(final Long id) {
        return userService.getAllReviews(id);
    }

    @Override
    public FilterUserResponseDetails getUser(final Long id) {
        final Optional<User> user = userService.findById(id);

        if(user.isEmpty()) {
            throw new UserNotFoundException();
        }

        return userMapper.mapEntityToFilteredUser(user.get());
    }

}