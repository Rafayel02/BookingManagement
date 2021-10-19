package am.aca.bookingmanagement.service.userservice;

import am.aca.bookingmanagement.entity.User;
import am.aca.bookingmanagement.exception.UserNotFoundException;
import am.aca.bookingmanagement.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User create(final User user) {
        return userRepository.save(user);
    }

    public User findById(final Long id) {
        final Optional<User> byId = userRepository.findById(id);
        if (byId.isEmpty()) {
            throw new UserNotFoundException("USER NOT FOUND");
        }
        return byId.get();
    }

    public User findByEmail(final String email) {
        final Optional<User> byEmail = userRepository.findByEmail(email);
        if (byEmail.isEmpty()) {
            throw new UserNotFoundException("USER_NOT_FOUND");
        }
        return byEmail.get();
    }

}



