package am.aca.bookingmanagement.service.user;

import am.aca.bookingmanagement.entity.User;

import java.util.Optional;

public interface UserService {

    User create(User user);

    Optional<User> findByEmail(String email);

    Optional<User> findById(Long id);

}