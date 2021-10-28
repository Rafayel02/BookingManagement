package am.aca.bookingmanagement.mapper.filter;

import org.springframework.stereotype.Component;
import am.aca.bookingmanagement.entity.Partner;
import am.aca.bookingmanagement.dto.filter.FilterResponseDetails;
import am.aca.bookingmanagement.dto.filter.FilteredPartnerResponseDetails;

import java.util.List;
import java.util.ArrayList;

@Component
public class FilterMapperImpl implements FilterMapper {

    @Override
    public FilterResponseDetails mapEntityListToFilterResponse(final List<Partner> partnersList) {
        final FilterResponseDetails filterResponseDetails = new FilterResponseDetails();
        final List<FilteredPartnerResponseDetails> filteredPartnersList = new ArrayList<>();
        for (final Partner partner : partnersList) {
            FilteredPartnerResponseDetails response = new FilteredPartnerResponseDetails();
            response.setId(partner.getId());
            response.setName(partner.getName());
            response.setEmail(partner.getEmail());
            response.setLongitude(partner.getLongitude());
            response.setLatitude(partner.getLatitude());
            response.setAddress(partner.getAddress());
            response.setImageUrl(partner.getImageUrl());
            response.setReview(partner.getRating());
            filteredPartnersList.add(response);
        }
        filterResponseDetails.setPartnersList(filteredPartnersList);
        return filterResponseDetails;
    }
}
