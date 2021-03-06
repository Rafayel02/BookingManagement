package am.aca.bookingmanagement.mapper.partner;

import am.aca.bookingmanagement.dto.filter.FilteredPartnerResponseDetails;
import am.aca.bookingmanagement.dto.partner.login.PartnerLoginResponseDetails;
import am.aca.bookingmanagement.dto.partner.register.PartnerRegisterRequestDetails;
import am.aca.bookingmanagement.dto.partner.register.PartnerRegisterResponseDetails;
import am.aca.bookingmanagement.entity.Partner;
import am.aca.bookingmanagement.jwt.JwtTokenGenerator;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PartnerMapperImpl implements PartnerMapper {

    private final PasswordEncoder passwordEncoder;
    private final JwtTokenGenerator jwtTokenGenerator;

    public PartnerMapperImpl(final PasswordEncoder passwordEncoder,
                             final JwtTokenGenerator jwtTokenGenerator) {
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenGenerator = jwtTokenGenerator;
    }

    @Override
    public Partner mapRegisterRequestToEntity(final PartnerRegisterRequestDetails request) {
        final Partner partner = new Partner();
        partner.setName(request.getName());
        partner.setEmail(request.getEmail());
        partner.setPassword(passwordEncoder.encode(request.getPassword()));
        partner.setLongitude(request.getLongitude());
        partner.setLatitude(request.getLatitude());
        if (request.getImageUrl() == null) {
            partner.setImageUrl("default");
        }
        partner.setAddress(request.getAddress());
        return partner;
    }

    @Override
    public PartnerRegisterResponseDetails mapEntityToRegisterResponse(final Partner partner) {
        final PartnerRegisterResponseDetails response = new PartnerRegisterResponseDetails();
        response.setToken(jwtTokenGenerator.generate(partner));
        return response;
    }

    @Override
    public PartnerLoginResponseDetails mapEntityToLoginResponse(final Partner partner) {
        final PartnerLoginResponseDetails response = new PartnerLoginResponseDetails();
        response.setToken(jwtTokenGenerator.generate(partner));
        return response;
    }

    @Override
    public FilteredPartnerResponseDetails mapEntityToFilteredPartner(final Partner partner) {
        final FilteredPartnerResponseDetails response = new FilteredPartnerResponseDetails();
        response.setId(partner.getId());
        response.setName(partner.getName());
        response.setAddress(partner.getAddress());
        response.setImageUrl(partner.getImageUrl());
        response.setLatitude(partner.getLatitude());
        response.setLongitude(partner.getLongitude());
        response.setEmail(partner.getEmail());
        return response;
    }

}