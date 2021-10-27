package am.aca.bookingmanagement.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import am.aca.bookingmanagement.facade.review.ReviewFacade;
import am.aca.bookingmanagement.dto.review.ReviewRegisterRequestDetails;

@RestController
@RequestMapping("/review")
public class ReviewController {

    private final ReviewFacade reviewFacade;

    public ReviewController(final ReviewFacade reviewFacade) {
        this.reviewFacade = reviewFacade;
    }

    @PostMapping
    public ResponseEntity<?> registerReview(@RequestBody final ReviewRegisterRequestDetails request) {
        reviewFacade.registerReview(request);
        return ResponseEntity.ok("ok");
    }

}