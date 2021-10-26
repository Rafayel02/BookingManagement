package am.aca.bookingmanagement.config;

import am.aca.bookingmanagement.jwt.JwtTokenParser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.*;


import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Collections;
import java.util.UUID;

public class JwtAuthorizationFilter implements Filter {

    @Override
    public void doFilter(final ServletRequest request,
                         final ServletResponse response,
                         final FilterChain filterChain) throws IOException, ServletException {
        final String jwtToken = ((HttpServletRequest) request).getHeader("Authorization");
        if (jwtToken == null) {
            filterChain.doFilter(request, response);
            return;
        }

        final Jws<Claims> claimsJws = new JwtTokenParser().parse(jwtToken);
        final UUID uuid = UUID.fromString(claimsJws.getBody().get("id").toString());
        final String role = claimsJws.getBody().get("role").toString();
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(
                uuid, null, Collections.singleton(new SimpleGrantedAuthority(role))
        ));

        filterChain.doFilter(request, response);
    }
}
