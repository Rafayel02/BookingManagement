package am.aca.bookingmanagement.mapper.partnermapper;

import am.aca.bookingmanagement.dto.partnerdto.login.PartnerLoginResponseDetails;
import am.aca.bookingmanagement.dto.partnerdto.register.PartnerRegisterRequestDetails;
import am.aca.bookingmanagement.dto.partnerdto.register.PartnerRegisterResponseDetails;
import am.aca.bookingmanagement.entity.Partner;
import am.aca.bookingmanagement.jwt.JwtTokenGenerator;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.UUID;

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
        partner.setUuid(UUID.randomUUID().toString());
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
