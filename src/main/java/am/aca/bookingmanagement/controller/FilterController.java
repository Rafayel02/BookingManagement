package am.aca.bookingmanagement.controller;

import am.aca.bookingmanagement.dto.filterdto.FilterRequestDetails;
import am.aca.bookingmanagement.dto.filterdto.FilterResponseDetails;
import am.aca.bookingmanagement.exception.SomethingWentWrongException;
import am.aca.bookingmanagement.facade.filterfacade.FilterFacade;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/filter")
public class FilterController {

    private final FilterFacade filterFacade;

    public FilterController(FilterFacade filterFacade) {
        this.filterFacade = filterFacade;
    }

    @PostMapping
    public ResponseEntity<?> filterPartner(@RequestBody final FilterRequestDetails filterRequestDetails) {
        try {
            final FilterResponseDetails filterResponseDetails = filterFacade.findBy(filterRequestDetails);
            return ResponseEntity.ok(filterResponseDetails);
        } catch (SomethingWentWrongException e) {
            return new ResponseEntity<>("WRONG_FILTER", HttpStatus.UNAUTHORIZED);
        } catch (final Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}
