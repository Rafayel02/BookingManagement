package am.aca.bookingmanagement.service.tokenservice;

import am.aca.bookingmanagement.entity.User;
import org.springframework.stereotype.Component;

@Component
public class TokenServiceImpl implements TokenService {
    @Override
    public String generateJWTToken(final User user) {
        return "test";
    }
}