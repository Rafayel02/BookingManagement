package am.aca.bookingmanagement.controller;

import am.aca.bookingmanagement.model.User;
import am.aca.bookingmanagement.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/register")
public class UserRegistrationController {

    private final UserRepository userRepository;

    public UserRegistrationController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @PostMapping("/user")
    public ResponseEntity<?> registerUser(@RequestBody final User user) {
        User user1 = userRepository.save(user);
        return ResponseEntity.ok(user1);
    }
}
