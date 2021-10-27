package am.aca.bookingmanagement.mapper.partner;

import org.springframework.stereotype.Component;
import am.aca.bookingmanagement.entity.Partner;
import am.aca.bookingmanagement.jwt.JwtTokenGenerator;
import org.springframework.security.crypto.password.PasswordEncoder;
import am.aca.bookingmanagement.dto.partner.login.PartnerLoginResponseDetails;
import am.aca.bookingmanagement.dto.partner.register.PartnerRegisterRequestDetails;
import am.aca.bookingmanagement.dto.partner.register.PartnerRegisterResponseDetails;

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
        partner.setImageUrl(request.getImageUrl());
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

}