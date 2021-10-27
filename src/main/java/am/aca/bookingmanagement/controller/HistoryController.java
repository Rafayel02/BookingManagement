package am.aca.bookingmanagement.controller;

import am.aca.bookingmanagement.entity.Review;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import am.aca.bookingmanagement.facade.user.UserFacade;
import am.aca.bookingmanagement.facade.partner.PartnerFacade;

import java.util.List;

@RestController
@RequestMapping("/history")
public class HistoryController {

    private final UserFacade userFacade;
    private final PartnerFacade partnerFacade;

    public HistoryController(final UserFacade userFacade,
                             final PartnerFacade partnerFacade) {
        this.userFacade = userFacade;
        this.partnerFacade = partnerFacade;
    }
    @GetMapping("/partner")
    public ResponseEntity<?> getPartnerHistory(@RequestParam final Long id){
        final List<Review> partnerReviews = partnerFacade.getAllReviews(id);
        return ResponseEntity.ok(partnerReviews);
    }

    @GetMapping
    public ResponseEntity<?> getUserHistory(@RequestParam final Long id) {
        final List<Review> userReviews = userFacade.getAllReviews(id);
        return ResponseEntity.ok(userReviews);
    }

}