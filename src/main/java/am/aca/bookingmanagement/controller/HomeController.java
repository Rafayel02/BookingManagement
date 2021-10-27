package am.aca.bookingmanagement.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/")
public class HomeController {

    @GetMapping
    public ResponseEntity<?> getBearerTokenHeader() {
        return ResponseEntity.ok("home");
    }

}