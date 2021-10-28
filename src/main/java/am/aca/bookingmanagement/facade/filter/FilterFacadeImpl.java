package am.aca.bookingmanagement.facade.filter;

import org.springframework.stereotype.Component;
import am.aca.bookingmanagement.entity.Partner;
import am.aca.bookingmanagement.mapper.filter.FilterMapper;
import am.aca.bookingmanagement.service.filter.FilterService;
import am.aca.bookingmanagement.service.activity.ActivityService;
import am.aca.bookingmanagement.service.category.CategoryService;
import am.aca.bookingmanagement.dto.filter.FilterRequestDetails;
import am.aca.bookingmanagement.dto.filter.FilterResponseDetails;
import am.aca.bookingmanagement.exception.SomethingWentWrongException;

import java.util.*;

@Component
public class FilterFacadeImpl implements FilterFacade {

    private final FilterMapper filterMapper;
    private final FilterService filterService;
    private final CategoryService categoryService;
    private final ActivityService activityService;

    public FilterFacadeImpl(final FilterMapper filterMapper,
                            final FilterService filterService,
                            final CategoryService categoryService,
                            final ActivityService activityService) {
        this.filterMapper = filterMapper;
        this.filterService = filterService;
        this.categoryService = categoryService;
        this.activityService = activityService;
    }

    @Override
    public FilterResponseDetails findAll() {
        final List<Partner> partnerList = filterService.findAll();
        if (partnerList.isEmpty()) {
            throw new SomethingWentWrongException();
        }
        return filterMapper.mapEntityListToFilterResponse(partnerList);
    }

    @Override
    public FilterResponseDetails findByCategory(final FilterRequestDetails request) {
        final List<Partner> partnerSetByCategory = filterService.findByCategory(getCategoryIdFromType(request));
        if (partnerSetByCategory.isEmpty()) {
            throw new SomethingWentWrongException();
        }
        return filterMapper.mapEntityListToFilterResponse(partnerSetByCategory);
    }

    @Override
    public FilterResponseDetails findByActivity(final FilterRequestDetails request) {
        final List<Partner> partnerSetByCategory = filterService.findByActivity(getActivityIdFromType(request));
        if (partnerSetByCategory.isEmpty()) {
            throw new SomethingWentWrongException();
        }
        return filterMapper.mapEntityListToFilterResponse(partnerSetByCategory);
    }

    @Override
    public FilterResponseDetails findByLocation(final FilterRequestDetails request) {
        return filterMapper.mapEntityListToFilterResponse(filterService.findByLocation(request));
    }

    @Override
    public FilterResponseDetails findByCategoryAndActivity(final FilterRequestDetails request) {
        final List<Integer> categoryIdList = getCategoryIdFromType(request);
        final List<Integer> activityIdList = getActivityIdFromType(request);
        final List<Partner> allByCategoriesAndActivities =
                filterService.findAllByCategoriesAndActivities(categoryIdList, activityIdList);
        return filterMapper.mapEntityListToFilterResponse(allByCategoriesAndActivities);
    }

    @Override
    public FilterResponseDetails findByCategoriesAndLocation(final FilterRequestDetails request) {
        final List<Integer> categoryIdList = getCategoryIdFromType(request);
        final List<Partner> partnersList = filterService.findById(categoryIdList);
        final List<Partner> filteredPartnerSet = filterService.filterPartnersByLocation(request, partnersList);
        return filterMapper.mapEntityListToFilterResponse(filteredPartnerSet);
    }

    @Override
    public FilterResponseDetails findByActivitiesAndLocation(final FilterRequestDetails request) {
        final List<Integer> activityIdList = getActivityIdFromType(request);
        final List<Partner> partnersList = filterService.findById(activityIdList);
        final List<Partner> filteredPartnerSet = filterService.filterPartnersByLocation(request, partnersList);
        return filterMapper.mapEntityListToFilterResponse(filteredPartnerSet);
    }

    @Override
    public FilterResponseDetails findByActivityCategoryAndLocation(final FilterRequestDetails request) {
        final List<Integer> categoryIdList = getCategoryIdFromType(request);
        final List<Integer> activityIdList = getActivityIdFromType(request);
        final List<Partner> allByCategoriesAndActivities =
                filterService.findAllByCategoriesAndActivities(categoryIdList, activityIdList);
        final List<Partner> filteredPartnerSet =
                filterService.filterPartnersByLocation(request, allByCategoriesAndActivities);
        return filterMapper.mapEntityListToFilterResponse(filteredPartnerSet);
    }

    @Override
    public FilterResponseDetails findBy(final FilterRequestDetails request) {
        if (areAllListsPresent(request)) {
            return findByActivityCategoryAndLocation(request);
        }

        if (areCategoryActivityListsPresent(request)) {
            return findByCategoryAndActivity(request);
        }

        if (isCategoryListPresent(request)) {
            return findByCategory(request);
        }

        if (isActivityListPresent(request)) {
            return findByActivity(request);
        }

        if (isLocationListPresent(request)) {
            return findByLocation(request);
        }

        if (areCategoryLocationListsPresent(request)) {
            return findByCategoriesAndLocation(request);
        }

        if (areActivityLocationListsPresent(request)) {
            return findByActivitiesAndLocation(request);
        }
        return findAll();
    }

    private boolean areCategoryLocationListsPresent(final FilterRequestDetails request) {
        return !request.getCategory().isEmpty() &&
                request.getActivity().isEmpty() &&
                request.getLocationInfo().size() == 3;
    }

    private boolean areActivityLocationListsPresent(final FilterRequestDetails request) {
        return request.getCategory().isEmpty() &&
                !request.getActivity().isEmpty() &&
                request.getLocationInfo().size() == 3;
    }

    private boolean isLocationListPresent(final FilterRequestDetails request) {
        return request.getCategory().isEmpty() &&
                request.getActivity().isEmpty() &&
                request.getLocationInfo().size() == 3;
    }

    private boolean isActivityListPresent(final FilterRequestDetails request) {
        return request.getCategory().isEmpty() &&
                !request.getActivity().isEmpty() &&
                request.getLocationInfo().size() != 3;
    }

    private boolean isCategoryListPresent(final FilterRequestDetails request) {
        return !request.getCategory().isEmpty() &&
                request.getActivity().isEmpty() &&
                request.getLocationInfo().size() != 3;
    }

    private boolean areCategoryActivityListsPresent(final FilterRequestDetails request) {
        return !request.getCategory().isEmpty() &&
                !request.getActivity().isEmpty() &&
                request.getLocationInfo().size() != 3;
    }

    private boolean areAllListsPresent(final FilterRequestDetails request) {
        return !request.getCategory().isEmpty() &&
                !request.getActivity().isEmpty() &&
                request.getLocationInfo().size() == 3;
    }

    private List<Integer> getCategoryIdFromType(final FilterRequestDetails request) {
        final List<Integer> categoryIdList = new ArrayList<>();
        Optional<Integer> categoryIdByType;
        for (int i = 0; i < request.getCategory().size(); i++) {
            categoryIdByType = categoryService.findCategoryIdByType(request.getCategory().get(i));
            if (categoryIdByType.isEmpty()) {
                continue;
            }
            categoryIdList.add(categoryIdByType.get());
        }
        return categoryIdList;
    }

    private List<Integer> getActivityIdFromType(final FilterRequestDetails filterRequestDetails) {
        final List<Integer> activityIdList = new ArrayList<>();
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