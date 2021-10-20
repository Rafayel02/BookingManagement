package am.aca.bookingmanagement.controller;

import am.aca.bookingmanagement.jwt.JwtTokenParser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HomeController {

    private final JwtTokenParser jwtTokenParser;

    public HomeController(final JwtTokenParser jwtTokenParser) {
        this.jwtTokenParser = jwtTokenParser;
    }

    @PostMapping
    public ResponseEntity<?> home(@RequestParam final String token) {

        final Jws<Claims> claims = jwtTokenParser.parse(token);
        final Claims body = claims.getBody();
        final String uuid = (String) body.get("id");
        //TODO check if {uuid} id present in users or partners table and response accordingly
        return ResponseEntity.ok(uuid);
    }
}
