package am.aca.bookingmanagement.service.userservice;

import am.aca.bookingmanagement.entity.User;
import am.aca.bookingmanagement.exception.UserAlreadyExistsException;
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
        final Optional<User> byEmail = findByEmail(user.getEmail());
        if (byEmail.isPresent()) {
            throw new UserAlreadyExistsException("USER WITH EMAIL: " + user.getEmail() + " ALREADY EXISTS");
        }
        return userRepository.save(user);
    }

    public Optional<User> findByEmail(final String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<User> findByUuid(final String uuid) {
        return userRepository.findByUuid(uuid);
    }

}



