package am.aca.bookingmanagement.controller;

import am.aca.bookingmanagement.model.Reviews;
import am.aca.bookingmanagement.model.User;
import am.aca.bookingmanagement.model.UserDto;
import am.aca.bookingmanagement.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/login")
public class UserLogin {
    private final UserRepository userRepository;

    public UserLogin(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/user")
    public ResponseEntity<?> loginUser(@RequestBody final UserDto userDto){
        System.out.println("******************************");
        User userByEmail = userRepository.getUserByEmail(userDto.getEmail());
        System.out.println(userByEmail.getEmail());
        return ResponseEntity.ok(userByEmail);

//        return  userDto.getEmail();
        }

}
