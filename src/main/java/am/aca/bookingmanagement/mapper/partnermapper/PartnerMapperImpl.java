package am.aca.bookingmanagement.mapper.partnermapper;

import am.aca.bookingmanagement.dto.partnerdto.PartnerLoginResponseDetails;
import am.aca.bookingmanagement.dto.partnerdto.PartnerRegisterRequestDetails;
import am.aca.bookingmanagement.dto.partnerdto.PartnerRegisterResponseDetails;
import am.aca.bookingmanagement.entity.Partner;
import org.springframework.stereotype.Component;

@Component
public class PartnerMapperImpl implements PartnerMapper {

    @Override
    public Partner mapRegisterRequestToEntity(final PartnerRegisterRequestDetails request) {
        final am.aca.bookingmanagement.entity.Partner partner = new am.aca.bookingmanagement.entity.Partner();
        partner.setName(request.getName());
        partner.setEmail(request.getEmail());
        partner.setPassword(request.getPassword());
        partner.setLongitude(request.getLongitude());
        partner.setLatitude(request.getLatitude());
        partner.setImageUrl(request.getImageUrl());
        partner.setAddress(request.getAddress());
        return partner;
    }

    @Override
    public PartnerRegisterResponseDetails mapEntityToRegisterResponse(final Partner partner) {
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
    public PartnerLoginResponseDetails mapEntityToLoginResponse(final Partner partner) {
        final PartnerLoginResponseDetails response = new PartnerLoginResponseDetails();
        response.setName(partner.getName());
        response.setEmail(partner.getEmail());
        response.setLatitude(partner.getLatitude());
        response.setLongitude(partner.getLongitude());
        response.setImageUrl(partner.getImageUrl());
        response.setAddress(partner.getAddress());
        return response;
    }

}
