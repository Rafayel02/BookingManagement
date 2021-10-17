package am.aca.bookingmanagement.service;

import am.aca.bookingmanagement.entity.User;
import am.aca.bookingmanagement.exception.UserNotFoundException;
import am.aca.bookingmanagement.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl {

    private UserRepository userRepository;

    public User findById(final Long id) {
        Optional<User> byId = userRepository.findById(id);
        if (byId.isPresent()) {
            return byId.get();
        }
        throw new UserNotFoundException("USER NOT FOUND");
    }

    public User findByEmail(final String email) {
        Optional<User> byEmail = userRepository.findByEmail(email);
        if (byEmail.isPresent()) {
            return byEmail.get();
        }
        throw new UserNotFoundException("USER_NOT_FOUND");

    }

}



