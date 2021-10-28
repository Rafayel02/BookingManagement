package am.aca.bookingmanagement.mapper.filter;

import am.aca.bookingmanagement.entity.Partner;
import am.aca.bookingmanagement.dto.filter.FilterResponseDetails;

import java.util.List;

public interface FilterMapper {

    FilterResponseDetails mapEntityListToFilterResponse(List<Partner> partnerList);
}