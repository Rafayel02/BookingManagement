package am.aca.bookingmanagement.service.tokenservice;

import am.aca.bookingmanagement.entity.User;

public interface TokenService {

    String generateJWTToken(User user);

}
