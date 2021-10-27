package am.aca.bookingmanagement.service.filter;

import org.springframework.stereotype.Service;
import am.aca.bookingmanagement.entity.Partner;
import am.aca.bookingmanagement.repository.FilterRepository;
import am.aca.bookingmanagement.dto.filter.FilterRequestDetails;

import java.util.List;
import java.util.ArrayList;

@Service
public class FilterServiceImpl implements FilterService {

    private final FilterRepository filterRepository;

    public FilterServiceImpl(final FilterRepository filterRepository) {
        this.filterRepository = filterRepository;
    }

    @Override
    public List<Partner> findAll() {
        return new ArrayList<>(filterRepository.findAll()) {
        };
    }

    @Override
    public List<Partner> findByCategory(final List<Integer> categoryIDList) {
        return filterRepository.findByCategory(categoryIDList);
    }

    @Override
    public List<Partner> findByActivity(final List<Integer> list) {
        return filterRepository.findByActivity(list);
    }


    @Override
    public List<Partner> findAllByCategoriesAndActivities(final List<Integer> categoryIDList,
                                                          final List<Integer> activityIDList) {
       return filterRepository.findByCategoryAndActivity(categoryIDList, activityIDList);

    }

    @Override
    public List<Partner> findByLocation(final FilterRequestDetails request) {
        List<Partner> allPartnersList = findAll();
        return filterPartnersByLocation(request, allPartnersList);
    }

    @Override
    public List<Partner> findAllByCategoriesActivitiesAndLocation(final FilterRequestDetails request,
                                                                  final List<Integer> categoryIDList,
                                                                  final List<Integer> activityIDList) {
        final List<Partner> allByCategoriesAndActivities = findAllByCategoriesAndActivities(categoryIDList, activityIDList);
        return filterPartnersByLocation(request, allByCategoriesAndActivities);
    }

    @Override
    public List<Partner> findByCategoriesAndLocation(final FilterRequestDetails request,
                                                     final List<Integer> categoryIDList) {
        final List<Partner> allByCategories = findByCategory(categoryIDList);
        return filterPartnersByLocation(request, allByCategories);
    }

    @Override
    public List<Partner> findByActivitiesAndLocation(final FilterRequestDetails request,
                                                     final List<Integer> activityIDList) {
        final List<Partner> allByActivities = findByActivity(activityIDList);
        return filterPartnersByLocation(request, allByActivities);
    }


    private boolean isCoordinateValid(final Double latitude0,
                                      final Double longitude0,
                                      final Double latitude1,
                                      final Double longitude1,
                                      final Double distance) {
        return Math.pow((latitude1 - latitude0), 2) + Math.pow((longitude1 - longitude0), 2) <= Math.pow(distance, 2);
    }

    public List<Partner> filterPartnersByLocation(final FilterRequestDetails request,
                                                  final List<Partner> list) {
        final List<Partner> partners = new ArrayList<>();
        for (final Partner partner : list) {
            if (request.getLocationInfo().size() == 3 &&
                    isCoordinateValid(request.getLocationInfo().get(0), request.getLocationInfo().get(1),
                            partner.getLatitude(), partner.getLongitude(), request.getLocationInfo().get(2))) {
                partners.add(partner);
            }
        }
        return partners;
    }

    @Override
    public List<Partner> findById(final List<Integer> partnersIdList) {
        return filterRepository.findById(partnersIdList);
    }
}