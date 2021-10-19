package am.aca.bookingmanagement.mapper.usermapper;

import am.aca.bookingmanagement.dto.userdto.login.UserLoginResponseDetails;
import am.aca.bookingmanagement.dto.userdto.register.UserRegisterRequestDetails;
import am.aca.bookingmanagement.dto.userdto.register.UserRegisterResponseDetails;
import am.aca.bookingmanagement.entity.User;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements UserMapper {

    private final PasswordEncoder passwordEncoder;

    public UserMapperImpl(final PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserRegisterResponseDetails mapEntityToRegisterResponse(final am.aca.bookingmanagement.entity.User user) {
        final UserRegisterResponseDetails response = new UserRegisterResponseDetails();
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setEmail(user.getEmail());
        return response;
    }

    @Override
    public User mapRegisterRequestToEntity(final UserRegisterRequestDetails request) {
        final User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        return user;
    }

    @Override
    public UserLoginResponseDetails mapEntityToLoginResponse(final User user) {
        final UserLoginResponseDetails response = new UserLoginResponseDetails();
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getEmail());
        response.setEmail(user.getEmail());
        return response;
    }

}