package am.aca.bookingmanagement.controller;

import am.aca.bookingmanagement.dto.filter.FilterUserResponseDetails;
import am.aca.bookingmanagement.dto.filter.FilteredPartnerResponseDetails;
import am.aca.bookingmanagement.facade.partner.PartnerFacade;
import am.aca.bookingmanagement.facade.user.UserFacade;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/info")
public class InfoController {

    private final PartnerFacade partnerFacade;
    private final UserFacade userFacade;

    public InfoController(final PartnerFacade partnerFacade, final UserFacade userFacade) {
        this.partnerFacade = partnerFacade;
        this.userFacade = userFacade;
    }

    @GetMapping
    public ResponseEntity<?> getUser(@RequestParam final Long id) {
        final FilterUserResponseDetails user = userFacade.getUser(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/partner")
    public ResponseEntity<?> getPartner(@RequestParam final Long id) {
        final FilteredPartnerResponseDetails partner = partnerFacade.getPartner(id);
        return ResponseEntity.ok(partner);
    }
}
