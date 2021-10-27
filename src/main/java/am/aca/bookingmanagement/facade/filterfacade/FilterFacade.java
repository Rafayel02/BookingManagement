package am.aca.bookingmanagement.facade.filterfacade;

import am.aca.bookingmanagement.dto.filterdto.FilterRequestDetails;
import am.aca.bookingmanagement.dto.filterdto.FilterResponseDetails;
import am.aca.bookingmanagement.entity.Partner;

import java.util.List;

public interface FilterFacade {
    FilterResponseDetails findAll(); //

    FilterResponseDetails findByCategory(FilterRequestDetails filterRequestDetails);

    FilterResponseDetails findByActivity(FilterRequestDetails filterRequestDetails);

    FilterResponseDetails findByLocation(FilterRequestDetails filterRequestDetails);

    FilterResponseDetails findByCategoryAndActivity(FilterRequestDetails filterRequestDetails);

    FilterResponseDetails findByCategoriesAndLocation(FilterRequestDetails filterRequestDetails);

    FilterResponseDetails findByActivitiesAndLocation(FilterRequestDetails filterRequestDetails);

    FilterResponseDetails findVyActivityCategoryAndLocation(FilterRequestDetails filterRequestDetails);

    FilterResponseDetails findBy(FilterRequestDetails filterRequestDetails);
}
