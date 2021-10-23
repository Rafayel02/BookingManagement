package am.aca.bookingmanagement.facade.filterfacade;

import am.aca.bookingmanagement.dto.filterdto.FilterRequestDetails;
import am.aca.bookingmanagement.dto.filterdto.FilterResponseDetails;
import am.aca.bookingmanagement.entity.Partner;
import am.aca.bookingmanagement.exception.SomethingWentWrongException;
import am.aca.bookingmanagement.mapper.filter.FilterMapper;
import am.aca.bookingmanagement.service.Category.CategoryService;
import am.aca.bookingmanagement.service.filter.FilterService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class FilterFacadeImpl implements FilterFacade {
    private final FilterService filterService;
    private final FilterMapper filterMapper;
    private final CategoryService categoryService;

    public FilterFacadeImpl(FilterService filterService, FilterMapper filterMapper, CategoryService categoryService) {
        this.filterService = filterService;
        this.filterMapper = filterMapper;
        this.categoryService = categoryService;
    }


    @Override
    public FilterResponseDetails findAll(final FilterRequestDetails filterRequestDetails) {
        Optional<List<Partner>> partnerList = filterService.findAll(filterRequestDetails);
        if (partnerList.isEmpty()) {
            throw new SomethingWentWrongException("Partner not found");
        }
        return filterMapper.mapEntityListToFilterResponse(partnerList.get());

    }

    @Override
    public FilterResponseDetails findByCategory(final FilterRequestDetails filterRequestDetails) {
        List<Integer> categoryIdList = getIdFromCategoryType(filterRequestDetails);
        Optional<List<Partner>> partnerListByCategory = filterService.findByCategory(categoryIdList);
        if (partnerListByCategory.isEmpty()) {
            throw new SomethingWentWrongException("Partner not found");
        }
        return filterMapper.mapEntityListToFilterResponse(partnerListByCategory.get());
    }

    private List<Integer> getIdFromCategoryType(final FilterRequestDetails filterRequestDetails) {
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

/*
    @Override
    public FilterResponseDetails findByReview(FilterResponseDetails filterResponseDetails) {
        return null;
    }

    @Override
    public FilterResponseDetails findByCategoryAndReview(FilterResponseDetails filterResponseDetails) {
        return null;
    }
 */
}