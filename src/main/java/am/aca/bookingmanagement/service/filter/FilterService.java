package am.aca.bookingmanagement.service.filter;

import am.aca.bookingmanagement.dto.filter.FilterRequestDetails;
import am.aca.bookingmanagement.entity.Partner;

import java.util.List;

public interface FilterService {

    List<Partner> findAll();

    List<Partner> findByCategory(List<Integer> categoryIDList);

    List<Partner> findByActivity(List<Integer> activityIDList);

    List<Partner> findByLocation(FilterRequestDetails request);

    List<Partner> findAllByCategoriesAndActivities(List<Integer> categoryIDList, List<Integer> activityIDList);

    List<Partner> findByCategoriesAndLocation(FilterRequestDetails request, List<Integer> categoryIDList);

    List<Partner> findByActivitiesAndLocation(FilterRequestDetails request, List<Integer> activityIDList);

    List<Partner> filterPartnersByLocation(FilterRequestDetails request, List<Partner> partnerList);

    List<Partner> findAllByCategoriesActivitiesAndLocation(FilterRequestDetails request,
                                                          List<Integer> categoryIDList, List<Integer> activityIDList);
    List<Partner> findById(List<Integer> partnersIdList);

}