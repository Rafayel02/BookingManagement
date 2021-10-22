package am.aca.bookingmanagement.controller;

import am.aca.bookingmanagement.dto.reviewdto.ReviewRegisterRequestDetails;
import am.aca.bookingmanagement.facade.reviewfacade.ReviewFacade;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/review")
public class ReviewController {
    private final ReviewFacade reviewFacade;

    public ReviewController(final ReviewFacade reviewFacade) {
        this.reviewFacade = reviewFacade;
    }

    @PostMapping
    public ResponseEntity<?> registerReview(@RequestBody final ReviewRegisterRequestDetails request) {
        try {
            reviewFacade.registerReview(request);
            return ResponseEntity.ok(HttpStatus.OK);
        } catch (final Exception e) {
            return new ResponseEntity<>("USER_WITH_ID: " + request.getUserUuid() +
                        " ALREADY_HAS_A_REVIEW_FOR_PARTNER_WITH_ID: " +
                        request.getPartnerUuid(), HttpStatus.FORBIDDEN);
        }
    }

}
