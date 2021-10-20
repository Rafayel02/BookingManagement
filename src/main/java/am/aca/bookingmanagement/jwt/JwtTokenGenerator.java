package am.aca.bookingmanagement.jwt;

import am.aca.bookingmanagement.entity.User;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenGenerator {

    public String generate(final User user) {
        return Jwts.builder()
                .claim("id", user.getUuid())
                .signWith(JwtKey.KEY).compact();
    }

}
