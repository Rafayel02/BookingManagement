package am.aca.bookingmanagement.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import am.aca.bookingmanagement.facade.filter.FilterFacade;
import am.aca.bookingmanagement.dto.filter.FilterRequestDetails;
import am.aca.bookingmanagement.dto.filter.FilterResponseDetails;

@RestController
@RequestMapping("/filter")
public class FilterController {

    private final FilterFacade filterFacade;

    public FilterController(final FilterFacade filterFacade) {
        this.filterFacade = filterFacade;
    }

    @PostMapping
    public ResponseEntity<?> filterPartner(@RequestBody final FilterRequestDetails request) {
        final FilterResponseDetails response = filterFacade.findBy(request);
        return ResponseEntity.ok(response);
    }

}
