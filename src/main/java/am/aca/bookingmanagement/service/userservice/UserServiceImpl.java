package am.aca.bookingmanagement.service.userservice;

import am.aca.bookingmanagement.entity.User;
import am.aca.bookingmanagement.exception.UserNotFoundException;
import am.aca.bookingmanagement.mapper.usermapper.UserMapper;
import am.aca.bookingmanagement.repository.UserRepository;
import am.aca.bookingmanagement.service.userservice.dto.UserCreateDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(final UserRepository userRepository, final UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public User create(final UserCreateDetails createDetails) {
        return userRepository.save(userMapper.mapCreteDetailsToEntity(createDetails));
    }

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



