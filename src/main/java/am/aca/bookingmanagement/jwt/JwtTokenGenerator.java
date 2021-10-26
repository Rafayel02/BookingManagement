package am.aca.bookingmanagement.jwt;

import am.aca.bookingmanagement.entity.Partner;
import am.aca.bookingmanagement.entity.User;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenGenerator {

    public String generate(final User user) {
        return Jwts.builder()
                .claim("id", user.getId())
                .claim("role", "ROLE_USER")
                .signWith(JwtKey.KEY).compact();
    }

    public String generate(final Partner partner) {
        return Jwts.builder()
                .claim("id", partner.getId())
                .claim("role", "ROLE_PARTNER")
                .signWith(JwtKey.KEY).compact();
    }
}
