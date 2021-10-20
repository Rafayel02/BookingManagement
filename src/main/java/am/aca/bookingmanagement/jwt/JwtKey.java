package am.aca.bookingmanagement.jwt;

import io.jsonwebtoken.security.Keys;

import java.security.Key;

public class JwtKey {
    public static final Key KEY = Keys.hmacShaKeyFor("'7V:lT@4sfsdterU6b~O(_nt5W0lJl@`wE".getBytes());
}
