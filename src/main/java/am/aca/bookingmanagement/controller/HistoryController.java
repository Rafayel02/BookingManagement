package am.aca.bookingmanagement.controller;

import am.aca.bookingmanagement.entity.Review;
import am.aca.bookingmanagement.facade.user.UserFacade;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/history")
public class HistoryController {

    private final UserFacade userFacade;

    public HistoryController(final UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    @GetMapping
    public ResponseEntity<?> getUserHistory(@RequestParam final Long id) {
        final List<Review> userReviews = userFacade.getAllReviews(id);
        return ResponseEntity.ok(userReviews);
    }

}
