package am.aca.bookingmanagement.mapper.user;

import am.aca.bookingmanagement.dto.filter.FilterUserResponseDetails;
import org.springframework.stereotype.Component;
import am.aca.bookingmanagement.entity.User;
import am.aca.bookingmanagement.jwt.JwtTokenGenerator;
import org.springframework.security.crypto.password.PasswordEncoder;
import am.aca.bookingmanagement.dto.user.login.UserLoginResponseDetails;
import am.aca.bookingmanagement.dto.user.register.UserRegisterRequestDetails;
import am.aca.bookingmanagement.dto.user.register.UserRegisterResponseDetails;

@Component
public class UserMapperImpl implements UserMapper {

    private final PasswordEncoder passwordEncoder;
    private final JwtTokenGenerator jwtTokenGenerator;

    public UserMapperImpl(final PasswordEncoder passwordEncoder,
                          final JwtTokenGenerator jwtTokenGenerator) {
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenGenerator = jwtTokenGenerator;
    }

    @Override
    public UserRegisterResponseDetails mapEntityToRegisterResponse(final User user) {
        final UserRegisterResponseDetails response = new UserRegisterResponseDetails();
        response.setToken(jwtTokenGenerator.generate(user));
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
        response.setToken(jwtTokenGenerator.generate(user));
        return response;
    }

    @Override
    public FilterUserResponseDetails mapEntityToFilteredUser(final User user) {
        final FilterUserResponseDetails filteredResponse = new FilterUserResponseDetails();
        filteredResponse.setFirstName(user.getFirstName());
        filteredResponse.setLastName(user.getLastName());
        filteredResponse.setEmail(user.getEmail());
        return filteredResponse;
    }

}