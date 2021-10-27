package am.aca.bookingmanagement.mapper.filter;

import am.aca.bookingmanagement.dto.filterdto.FilterResponseDetails;
import am.aca.bookingmanagement.dto.filterdto.PartnerDto;
import am.aca.bookingmanagement.entity.Partner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class FilterMapperImpl implements FilterMapper {

    @Override
    public FilterResponseDetails mapEntityListToFilterResponse(final Set<Partner> partnerList) {
        FilterResponseDetails filterResponseDetails = new FilterResponseDetails();
        List<PartnerDto> partnerDtoList = new ArrayList<>();
        for (Partner partner : partnerList) {
            PartnerDto partnerDto = new PartnerDto();
            partnerDto.setName(partner.getName());
            partnerDto.setEmail(partner.getEmail());
            partnerDto.setLongitude(partner.getLongitude());
            partnerDto.setLatitude(partner.getLatitude());
            partnerDto.setAddress(partner.getAddress());
            partnerDto.setImageUrl(partner.getImageUrl());
            partnerDtoList.add(partnerDto);
        }
        filterResponseDetails.setPartnersList(partnerDtoList);
        return filterResponseDetails;
    }
}
