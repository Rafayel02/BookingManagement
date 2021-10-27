package am.aca.bookingmanagement.facade.filterfacade;

import am.aca.bookingmanagement.dto.filterdto.FilterRequestDetails;
import am.aca.bookingmanagement.dto.filterdto.FilterResponseDetails;
import am.aca.bookingmanagement.entity.Partner;
import am.aca.bookingmanagement.exception.SomethingWentWrongException;
import am.aca.bookingmanagement.mapper.filter.FilterMapper;
import am.aca.bookingmanagement.service.activity.ActivityService;
import am.aca.bookingmanagement.service.category.CategoryService;
import am.aca.bookingmanagement.service.filter.FilterService;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class FilterFacadeImpl implements FilterFacade {
    private final FilterService filterService;
    private final FilterMapper filterMapper;
    private final CategoryService categoryService;
    private final ActivityService activityService;

    public FilterFacadeImpl(FilterService filterService, FilterMapper filterMapper, CategoryService categoryService, final ActivityService activityService) {
        this.filterService = filterService;
        this.filterMapper = filterMapper;
        this.categoryService = categoryService;
        this.activityService = activityService;
    }


    @Override
    public FilterResponseDetails findAll() {
        Set<Partner> partnerList = filterService.findAll();
        if (partnerList.isEmpty()) {
            throw new SomethingWentWrongException("Partner not found");
        }
        return filterMapper.mapEntityListToFilterResponse(partnerList);
    }

    @Override
    public FilterResponseDetails findByCategory(final FilterRequestDetails filterRequestDetails) {
        List<Integer> categoryIdList = getCategoryIdFromType(filterRequestDetails);
        Set<Partner> partnerSetByCategory = filterService.findByCategory(getCategoryIdFromType(filterRequestDetails));
        if (partnerSetByCategory.isEmpty()) {
            throw new SomethingWentWrongException("Partner not found");
        }
        return filterMapper.mapEntityListToFilterResponse(partnerSetByCategory);
    }

    @Override
    public FilterResponseDetails findByActivity(final FilterRequestDetails filterRequestDetails) {
        List<Integer> activityList = getActivityIdFromType(filterRequestDetails);
        Set<Partner> partnerSetByCategory = filterService.findByActivity(getActivityIdFromType(filterRequestDetails));
        if (partnerSetByCategory.isEmpty()) {
            throw new SomethingWentWrongException("Partner not found");
        }
        return filterMapper.mapEntityListToFilterResponse(partnerSetByCategory);
    }

    @Override
    public FilterResponseDetails findByLocation(final FilterRequestDetails filterRequestDetails) {
        return filterMapper.mapEntityListToFilterResponse(filterService.findByLocation(filterRequestDetails));
    }

    @Override
    public FilterResponseDetails findByCategoryAndActivity(final FilterRequestDetails filterRequestDetails) {
        List<Integer> categoryIdList = getCategoryIdFromType(filterRequestDetails);
        List<Integer> activityIdList = getActivityIdFromType(filterRequestDetails);
        Set<Partner> allByCategoriesAndActivities = filterService.findAllByCategoriesAndActivities(categoryIdList, activityIdList);
        return filterMapper.mapEntityListToFilterResponse(allByCategoriesAndActivities);
    }

    @Override
    public FilterResponseDetails findByCategoriesAndLocation(final FilterRequestDetails filterRequestDetails) {
        List<Integer> categoryIdList = getCategoryIdFromType(filterRequestDetails);
        Set<Partner> partnersList = filterService.findById(categoryIdList);
        Set<Partner> filteredPartnerSet = filterService.partnersFilteringByLocation(filterRequestDetails, partnersList);
        return filterMapper.mapEntityListToFilterResponse(filteredPartnerSet);
    }


    @Override
    public FilterResponseDetails findByActivitiesAndLocation(final FilterRequestDetails filterRequestDetails) {
        List<Integer> activityIdList = getActivityIdFromType(filterRequestDetails);
        Set<Partner> partnersList = filterService.findById(activityIdList);
        Set<Partner> filteredPartnerSet = filterService.partnersFilteringByLocation(filterRequestDetails, partnersList);
        return filterMapper.mapEntityListToFilterResponse(filteredPartnerSet);
    }

    @Override
    public FilterResponseDetails findVyActivityCategoryAndLocation(final FilterRequestDetails filterRequestDetails) {
        List<Integer> categoryIdList = getCategoryIdFromType(filterRequestDetails);
        List<Integer> activityIdList = getActivityIdFromType(filterRequestDetails);
        Set<Partner> allByCategoriesAndActivities = filterService.findAllByCategoriesAndActivities(categoryIdList, activityIdList);
        Set<Partner> filteredPartnerSet = filterService.partnersFilteringByLocation(filterRequestDetails, allByCategoriesAndActivities);
        return filterMapper.mapEntityListToFilterResponse(filteredPartnerSet);
    }

    @Override
    public FilterResponseDetails findBy(final FilterRequestDetails filterRequestDetails) {
        if (filterRequestDetails.getCategory() != null &&
                filterRequestDetails.getActivity() != null &&
                filterRequestDetails.getLocation() != null &&
                filterRequestDetails.getLocation().size() == 3) {
            System.out.println("findVyActivityCategoryAndLocation");
            return findVyActivityCategoryAndLocation(filterRequestDetails);
        }
        if (filterRequestDetails.getCategory() != null &&
                filterRequestDetails.getActivity() != null
                && (filterRequestDetails.getLocation() == null ||
                filterRequestDetails.getLocation().size() != 3)) {
            System.out.println("findByCategoryAndActivity");
            return findByCategoryAndActivity(filterRequestDetails);
        }
        if (filterRequestDetails.getCategory() != null && filterRequestDetails.getActivity() == null
                && (filterRequestDetails.getLocation() == null || filterRequestDetails.getLocation().size() != 3 )) {
            System.out.println("findByCategory");

            return findByCategory(filterRequestDetails);
        }
        if (filterRequestDetails.getCategory() == null && filterRequestDetails.getActivity() != null
                && (filterRequestDetails.getLocation() == null || filterRequestDetails.getLocation().size() != 3)) {
            System.out.println("findByActivity");
            return findByActivity(filterRequestDetails);
        }
        if (filterRequestDetails.getCategory() == null && filterRequestDetails.getActivity() == null
                && filterRequestDetails.getLocation() != null && filterRequestDetails.getLocation().size() == 3) {
            System.out.println("findByLocation");
            return findByLocation(filterRequestDetails);
        }
        if (filterRequestDetails.getCategory() != null && filterRequestDetails.getActivity() == null
                && filterRequestDetails.getLocation() != null && filterRequestDetails.getLocation().size() == 3) {
            System.out.println("findByCategoriesAndLocation");
            return findByCategoriesAndLocation(filterRequestDetails);
        }
        if (filterRequestDetails.getCategory() == null && filterRequestDetails.getActivity() != null
                && filterRequestDetails.getLocation() != null && filterRequestDetails.getLocation().size() == 3) {
            System.out.println("findByActivitiesAndLocation");
            return findByActivitiesAndLocation(filterRequestDetails);
        }
        System.out.println("findAll");
        return findAll();
    }

    private List<Integer> getCategoryIdFromType(final FilterRequestDetails filterRequestDetails) {
        List<Integer> categoryIdList = new ArrayList<>();
        Optional<Integer> categoryIdByType;
        for (int i = 0; i < filterRequestDetails.getCategory().size(); i++) {
            categoryIdByType = categoryService.findCategoryIdByType(filterRequestDetails.getCategory().get(i));
            if (categoryIdByType.isEmpty()) {
                continue;
            }
            categoryIdList.add(categoryIdByType.get());
        }
        return categoryIdList;
    }

    private List<Integer> getActivityIdFromType(final FilterRequestDetails filterRequestDetails) {
        List<Integer> activityIdList = new ArrayList<>();
        Optional<Integer> activityIdByType;
        for (int i = 0; i < filterRequestDetails.getActivity().size(); i++) {
            activityIdByType = activityService.findActivityIdByType((filterRequestDetails.getActivity().get(i)));
            if (activityIdByType.isEmpty()) {
                continue;
            }
            activityIdList.add(activityIdByType.get());

        }
        return activityIdList;
    }
}