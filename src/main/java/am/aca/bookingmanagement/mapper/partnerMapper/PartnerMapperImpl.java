package am.aca.bookingmanagement.mapper.partnerMapper;

import am.aca.bookingmanagement.entity.Restaurant;
import am.aca.bookingmanagement.facade.partnerfacade.partnerregisterdto.PartnerRegisterRequestDetails;
import am.aca.bookingmanagement.facade.partnerfacade.partnerregisterdto.PartnerRegisterResponseDetails;
import am.aca.bookingmanagement.service.partnerservice.dto.PartnerCreateDetails;
import org.springframework.stereotype.Component;

@Component
public class PartnerMapperImpl implements PartnerMapper {

    @Override
    public PartnerCreateDetails mapRequestToDetails(final PartnerRegisterRequestDetails request) {
        final PartnerCreateDetails createdPartner = new PartnerCreateDetails();
        createdPartner.setName(request.getName());
        createdPartner.setEmail(request.getEmail());
        createdPartner.setPassword(request.getPassword());
        createdPartner.setLatitude(request.getLatitude());
        createdPartner.setLongitude(request.getLongitude());
        createdPartner.setImageUrl(request.getImageUrl());
        createdPartner.setAddress(request.getAddress());
        return createdPartner;
    }

    @Override
    public PartnerRegisterResponseDetails mapEntityToResponse(final Restaurant restaurant) {
        final PartnerRegisterResponseDetails response = new PartnerRegisterResponseDetails();
        response.setName(restaurant.getName());
        response.setEmail(restaurant.getEmail());
        response.setLatitude(restaurant.getLatitude());
        response.setLongitude(restaurant.getLongitude());
        response.setImageUrl(restaurant.getImageUrl());
        response.setAddress(restaurant.getAddress());
        return response;
    }

    @Override
    public Restaurant mapCreateDetailsToEntity(final PartnerCreateDetails createDetails) {
        final Restaurant entity = new Restaurant();
        entity.setName(createDetails.getName());
        entity.setEmail(createDetails.getEmail());
        entity.setPassword(createDetails.getPassword());
        entity.setLatitude(createDetails.getLatitude());
        entity.setLongitude(createDetails.getLongitude());
        entity.setImageUrl(createDetails.getImageUrl());
        entity.setAddress(createDetails.getAddress());
        return entity;
    }
}
