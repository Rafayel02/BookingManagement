package am.aca.bookingmanagement.service.user;

import org.springframework.stereotype.Service;
import am.aca.bookingmanagement.entity.User;
import am.aca.bookingmanagement.repository.UserRepository;
import am.aca.bookingmanagement.exception.UserAlreadyExistsException;

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
            throw new UserAlreadyExistsException();
        }
        return userRepository.save(user);
    }

    public Optional<User> findByEmail(final String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<User> findById(final Long id) {
        return userRepository.findById(id);
    }

}