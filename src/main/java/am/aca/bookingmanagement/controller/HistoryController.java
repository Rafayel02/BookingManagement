package am.aca.bookingmanagement.controller;

import am.aca.bookingmanagement.entity.Review;
import org.springframework.http.ResponseEntity;


import org.springframework.web.bind.annotation.*;
import am.aca.bookingmanagement.facade.partner.PartnerFacade;

import java.util.List;

@RestController
@RequestMapping("/history")
public class HistoryController {

    private final PartnerFacade partnerFacade;

    public HistoryController(final PartnerFacade partnerFacade) {
        this.partnerFacade = partnerFacade;
    }

    @GetMapping("/partner")
    public ResponseEntity<?> getPartnerHistory(@RequestParam final Long id){
        final List<Review> partnerReviews = partnerFacade.getAllReviews(id);
        return ResponseEntity.ok(partnerReviews);
    }
}
