package am.aca.bookingmanagement.controller;

import am.aca.bookingmanagement.jwt.JwtTokenParser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Objects;

@RestController
@RequestMapping("/")
public class HomeController {

    private final JwtTokenParser jwtTokenParser;

    public HomeController(final JwtTokenParser jwtTokenParser) {
        this.jwtTokenParser = jwtTokenParser;
    }

    @GetMapping
    public ResponseEntity<?> getBearerTokenHeader() {
        String authorizationToken =
                ((ServletRequestAttributes) Objects
                        .requireNonNull(
                                RequestContextHolder
                                        .getRequestAttributes()))
                        .getRequest()
                        .getHeader("Authorization")
                        .replace("Bearer ", "");
        final Jws<Claims> claims = jwtTokenParser.parse(authorizationToken);
        final Claims body = claims.getBody();
        final String uuid = (String) body.get("id");
        System.out.println(uuid);
        return ResponseEntity.ok(uuid);
    }

}
