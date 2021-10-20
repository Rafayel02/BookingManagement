package am.aca.bookingmanagement.mapper.usermapper;

import am.aca.bookingmanagement.dto.userdto.login.UserLoginResponseDetails;
import am.aca.bookingmanagement.dto.userdto.register.UserRegisterRequestDetails;
import am.aca.bookingmanagement.dto.userdto.register.UserRegisterResponseDetails;
import am.aca.bookingmanagement.entity.User;
import am.aca.bookingmanagement.jwt.JwtTokenGenerator;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UserMapperImpl implements UserMapper {

    private final PasswordEncoder passwordEncoder;
    private final JwtTokenGenerator jwtTokenGenerator;

    public UserMapperImpl(final PasswordEncoder passwordEncoder, final JwtTokenGenerator jwtTokenGenerator) {
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenGenerator = jwtTokenGenerator;
    }

    @Override
    public UserRegisterResponseDetails mapEntityToRegisterResponse(final User user) {
        final UserRegisterResponseDetails response = new UserRegisterResponseDetails();
        response.setToken(jwtTokenGenerator.generate(user));
        System.out.println(jwtTokenGenerator.generate(user));
        return response;
    }

    @Override
    public User mapRegisterRequestToEntity(final UserRegisterRequestDetails request) {
        final User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setUuid(UUID.randomUUID().toString());
        return user;
    }

    @Override
    public UserLoginResponseDetails mapEntityToLoginResponse(final User user) {
        final UserLoginResponseDetails response = new UserLoginResponseDetails();
        response.setToken(jwtTokenGenerator.generate(user));
        return response;
    }

}