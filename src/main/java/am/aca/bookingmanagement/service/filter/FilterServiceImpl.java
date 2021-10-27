package am.aca.bookingmanagement.service.filter;

import am.aca.bookingmanagement.dto.filterdto.FilterRequestDetails;
import am.aca.bookingmanagement.entity.Partner;
import am.aca.bookingmanagement.repository.FilterRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class FilterServiceImpl implements FilterService {
    private final FilterRepository filterRepository;

    public FilterServiceImpl(FilterRepository filterRepository) {
        this.filterRepository = filterRepository;
    }

    @Override
    public Set<Partner> findAll() {
        return new HashSet<>(filterRepository.findAll());
    }

    @Override
    public Set<Partner> findByCategory(List<Integer> categoryIDList) {
        return filterRepository.findByCategory(categoryIDList);
    }

    @Override
    public Set<Partner> findByActivity(final List<Integer> list) {
        return filterRepository.findByActivity(list);
    }


    @Override
    public Set<Partner> findAllByCategoriesAndActivities(final List<Integer> categoryIDList,
                                                         final List<Integer> activityIDList) {
       return filterRepository.findByCategoryAndActivity(categoryIDList, activityIDList);

    }

    @Override
    public Set<Partner> findByLocation(final FilterRequestDetails filterRequestDetails) {
        Set<Partner> allPartnersList = findAll();
        return partnersFilteringByLocation(filterRequestDetails, allPartnersList);
    }

    @Override
    public Set<Partner> findAllByCategoriesActivitiesAndLocation(final FilterRequestDetails filterRequestDetails,
                                                                 final List<Integer> categoryIDList,
                                                                 final List<Integer> activityIDList) {
        Set<Partner> allByCategoriesAndActivities = findAllByCategoriesAndActivities(categoryIDList, activityIDList);
        return partnersFilteringByLocation(filterRequestDetails, allByCategoriesAndActivities);
    }

    @Override
    public Set<Partner> findByCategoriesAndLocation(final FilterRequestDetails filterRequestDetails,
                                                    final List<Integer> categoryIDList) {
        Set<Partner> allByCategories = findByCategory(categoryIDList);
        return partnersFilteringByLocation(filterRequestDetails, allByCategories);
    }

    @Override
    public Set<Partner> findByActivitiesAndLocation(final FilterRequestDetails filterRequestDetails,
                                                    final List<Integer> activityIDList) {
        Set<Partner> allByActivities = findByActivity(activityIDList);
        return partnersFilteringByLocation(filterRequestDetails, allByActivities);
    }


    private boolean isCoordinateValid(final Double latitude0, final Double longitude0,
                                      final Double latitude1, final Double longitude1, final Double distance) {
        return Math.pow((latitude1 - latitude0), 2) + Math.pow((longitude1 - longitude0), 2) <= Math.pow(distance, 2);
    }

    public Set<Partner> partnersFilteringByLocation(FilterRequestDetails filterRequestDetails, Set<Partner> partnerList) {
        Set<Partner> filteredPartnerSet = new HashSet<>();
        for (Partner partner : partnerList) {
            if (filterRequestDetails.getLocationInfo().size() == 3 &&
                    isCoordinateValid(filterRequestDetails.getLocationInfo().get(0), filterRequestDetails.getLocationInfo().get(1),
                            partner.getLatitude(), partner.getLongitude(), filterRequestDetails.getLocationInfo().get(2))) {
                filteredPartnerSet.add(partner);
            }
        }
        return filteredPartnerSet;
    }

    @Override
    public Set<Partner> findById(final List<Integer> partnersIdList) {
        return filterRepository.findById(partnersIdList);
    }
}