package am.aca.bookingmanagement.mapper.partnerMapper;

import am.aca.bookingmanagement.entity.Partner;
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
    public PartnerRegisterResponseDetails mapEntityToResponse(final Partner partner) {
        final PartnerRegisterResponseDetails response = new PartnerRegisterResponseDetails();
        response.setName(partner.getName());
        response.setEmail(partner.getEmail());
        response.setLatitude(partner.getLatitude());
        response.setLongitude(partner.getLongitude());
        response.setImageUrl(partner.getImageUrl());
        response.setAddress(partner.getAddress());
        return response;
    }

    @Override
    public Partner mapCreateDetailsToEntity(final PartnerCreateDetails createDetails) {
        final Partner entity = new Partner();
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
