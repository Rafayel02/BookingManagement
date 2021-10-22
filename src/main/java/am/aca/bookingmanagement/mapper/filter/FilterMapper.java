package am.aca.bookingmanagement.mapper.filter;

import am.aca.bookingmanagement.dto.filterdto.FilterResponseDetails;
import am.aca.bookingmanagement.entity.Partner;

import java.util.List;

public interface FilterMapper {

    FilterResponseDetails mapEntityListToFilterResponse(List<Partner> partnerList);
}