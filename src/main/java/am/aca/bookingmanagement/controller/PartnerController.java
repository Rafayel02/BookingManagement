package am.aca.bookingmanagement.controller;

import am.aca.bookingmanagement.dto.filterdto.FilterRequestDetails;
import am.aca.bookingmanagement.dto.filterdto.FilterResponseDetails;
import am.aca.bookingmanagement.exception.SomethingWentWrongException;
import am.aca.bookingmanagement.facade.filterfacade.FilterFacade;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/partners")
public class PartnerController {

    private final FilterFacade filterFacade;

    public PartnerController(final FilterFacade filterFacade) {
        this.filterFacade = filterFacade;
    }

    @GetMapping
    public ResponseEntity<?> filterPartner(@RequestBody final FilterRequestDetails filterRequestDetails) {
        try {
            final FilterResponseDetails filterResponseDetails = filterFacade.findAll(filterRequestDetails);
            return ResponseEntity.ok(filterResponseDetails);
        } catch (SomethingWentWrongException e) {
            return new ResponseEntity<>("WRONG_FILTER", HttpStatus.UNAUTHORIZED);
        } catch (final Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
