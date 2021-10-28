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
    public FilterResponseDetails mapEntityListToFilterResponse(final List<Partner> partnerList) {
        final FilterResponseDetails filterResponseDetails = new FilterResponseDetails();
        final List<FilteredPartnerResponseDetails> filteredPartnerResponseDetailsList = new ArrayList<>();
        for (final Partner partner : partnerList) {
            FilteredPartnerResponseDetails filteredPartnerResponseDetails = new FilteredPartnerResponseDetails();
            filteredPartnerResponseDetails.setName(partner.getName());
            filteredPartnerResponseDetails.setEmail(partner.getEmail());
            filteredPartnerResponseDetails.setLongitude(partner.getLongitude());
            filteredPartnerResponseDetails.setLatitude(partner.getLatitude());
            filteredPartnerResponseDetails.setAddress(partner.getAddress());
            filteredPartnerResponseDetails.setImageUrl(partner.getImageUrl());
            filteredPartnerResponseDetailsList.add(filteredPartnerResponseDetails);
        }
        filterResponseDetails.setPartnersList(filteredPartnerResponseDetailsList);
        return filterResponseDetails;
    }
}
