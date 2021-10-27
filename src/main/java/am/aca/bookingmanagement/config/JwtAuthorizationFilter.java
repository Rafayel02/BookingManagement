package am.aca.bookingmanagement.config;

import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Claims;
import am.aca.bookingmanagement.jwt.JwtTokenParser;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.servlet.*;
import java.io.IOException;
import java.util.Collections;
import javax.servlet.http.HttpServletRequest;

public class JwtAuthorizationFilter implements Filter {

    @Override
    public void doFilter(final ServletRequest request,
                         final ServletResponse response,
                         final FilterChain filterChain) throws IOException, ServletException {
        final String jwtToken = ((HttpServletRequest) request).getHeader("Authorization");
        final String token = resolveToken(jwtToken);

        if (token == null) {
            filterChain.doFilter(request, response);
            return;
        }

        final Jws<Claims> claimsJws = new JwtTokenParser().parse(token);
        final Long id = Long.parseLong(claimsJws.getBody().get("id").toString());
        final String role = claimsJws.getBody().get("role").toString();
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(
                id, null, Collections.singleton(new SimpleGrantedAuthority(role))
        ));

        filterChain.doFilter(request, response);
    }

    private String resolveToken(final String jwtToken) {
        if (jwtToken == null || !jwtToken.startsWith("Bearer ")) {
            return null;
        }
        return jwtToken.substring(7);
    }

}