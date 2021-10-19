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

    public am.aca.bookingmanagement.entity.User findById(final Long id) {
        Optional<am.aca.bookingmanagement.entity.User> byId = userRepository.findById(id);
        if (byId.isPresent()) {
            return byId.get();
        }
        throw new UserNotFoundException("USER NOT FOUND");
    }

    public User findByEmail(final String email) {
        final Optional<am.aca.bookingmanagement.entity.User> byEmail = userRepository.findByEmail(email);
        if (byEmail.isPresent()) {
            return byEmail.get();
        }
        throw new UserNotFoundException("USER_NOT_FOUND");

    }

}



