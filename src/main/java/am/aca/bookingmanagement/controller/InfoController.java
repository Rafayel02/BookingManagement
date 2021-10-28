package am.aca.bookingmanagement.controller;

import am.aca.bookingmanagement.dto.filter.FilteredPartnerResponseDetails;
import am.aca.bookingmanagement.facade.partner.PartnerFacade;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/info")
public class InfoController {

    private final PartnerFacade partnerFacade;

    public InfoController(final PartnerFacade partnerFacade) {
        this.partnerFacade = partnerFacade;
    }

    @GetMapping("/partner")
    public ResponseEntity<?> getPartner(@RequestParam Long id){
        final FilteredPartnerResponseDetails partner = partnerFacade.getPartner(id);
        return ResponseEntity.ok(partner);
    }
}
