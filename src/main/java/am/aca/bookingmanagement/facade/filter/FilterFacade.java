package am.aca.bookingmanagement.facade.filter;

import am.aca.bookingmanagement.dto.filter.FilterRequestDetails;
import am.aca.bookingmanagement.dto.filter.FilterResponseDetails;

public interface FilterFacade {

    FilterResponseDetails findAll();

    FilterResponseDetails findByCategory(FilterRequestDetails request);

    FilterResponseDetails findByActivity(FilterRequestDetails request);

    FilterResponseDetails findByLocation(FilterRequestDetails request);

    FilterResponseDetails findByCategoryAndActivity(FilterRequestDetails request);

    FilterResponseDetails findByCategoriesAndLocation(FilterRequestDetails request);

    FilterResponseDetails findByActivitiesAndLocation(FilterRequestDetails request);

    FilterResponseDetails findByActivityCategoryAndLocation(FilterRequestDetails request);

    FilterResponseDetails findMatchingPartners(FilterRequestDetails request);

}
