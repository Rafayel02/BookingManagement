package am.aca.bookingmanagement.jwt;

import org.springframework.stereotype.Component;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;

@Component
public class JwtTokenParser {

    public Jws<Claims> parse(final String jws) {
        final JwtParser build = Jwts.parserBuilder().setSigningKey(JwtKey.KEY).build();
        return build.parseClaimsJws(jws);
    }

}