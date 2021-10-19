package am.aca.bookingmanagement.service.userservice;

import am.aca.bookingmanagement.entity.User;

public interface UserService {
    User create(User user);

    User findByEmail(String email);
}
