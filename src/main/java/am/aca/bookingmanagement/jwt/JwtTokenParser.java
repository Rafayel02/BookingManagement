package am.aca.bookingmanagement.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenParser {

    public Jws<Claims> parse(String jws) {
        final JwtParser build = Jwts.parserBuilder().setSigningKey(JwtKey.KEY).build();
        return build.parseClaimsJws(jws);
    }

}
