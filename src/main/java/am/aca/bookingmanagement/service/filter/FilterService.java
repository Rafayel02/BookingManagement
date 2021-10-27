package am.aca.bookingmanagement.service.filter;

import am.aca.bookingmanagement.dto.filterdto.FilterRequestDetails;
import am.aca.bookingmanagement.entity.Partner;

import java.util.List;
import java.util.Set;

public interface FilterService {

    Set<Partner> findAll();

    Set<Partner> findByCategory(List<Integer> categoryIDList);

    Set<Partner> findByActivity(List<Integer> activityIDList);

    Set<Partner> findByLocation(FilterRequestDetails filterRequestDetails);

    Set<Partner> findAllByCategoriesAndActivities(List<Integer> categoryIDList, List<Integer> activityIDList);

    Set<Partner> findByCategoriesAndLocation(FilterRequestDetails filterRequestDetails, List<Integer> categoryIDList);

    Set<Partner> findByActivitiesAndLocation(FilterRequestDetails filterRequestDetails, List<Integer> activityIDList);

    Set<Partner> findAllByCategoriesActivitiesAndLocation(FilterRequestDetails filterRequestDetails,
                                                           List<Integer> categoryIDList, List<Integer> activityIDList);

    Set<Partner> partnersFilteringByLocation(FilterRequestDetails filterRequestDetails, Set<Partner> partnerList);

    Set<Partner> findById(List<Integer> partnersIdList);
}