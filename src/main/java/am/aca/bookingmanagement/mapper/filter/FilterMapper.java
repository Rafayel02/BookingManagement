package am.aca.bookingmanagement.mapper.filter;

import am.aca.bookingmanagement.dto.filterdto.FilterResponseDetails;
import am.aca.bookingmanagement.entity.Partner;

import java.util.List;
import java.util.Set;

public interface FilterMapper {

    FilterResponseDetails mapEntityListToFilterResponse(Set<Partner> partnerList);
}