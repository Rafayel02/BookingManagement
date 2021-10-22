package am.aca.bookingmanagement.facade.filterfacade;

import am.aca.bookingmanagement.dto.filterdto.FilterRequestDetails;
import am.aca.bookingmanagement.dto.filterdto.FilterResponseDetails;

public interface FilterFacade {
    FilterResponseDetails findAll(FilterRequestDetails filterRequestDetails);

    FilterResponseDetails findByCategory(FilterRequestDetails filterRequestDetails);
/*
    FilterResponseDetails findByReview(FilterResponseDetails filterResponseDetails);

    FilterResponseDetails findByCategoryAndReview(FilterResponseDetails filterResponseDetails);
 */
}
