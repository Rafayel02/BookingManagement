package am.aca.bookingmanagement.facade.partnerfacade;

import am.aca.bookingmanagement.checker.ValidationChecker;
import am.aca.bookingmanagement.dto.partnerdto.login.PartnerLoginRequestDetails;
import am.aca.bookingmanagement.dto.partnerdto.login.PartnerLoginResponseDetails;
import am.aca.bookingmanagement.dto.partnerdto.register.PartnerRegisterRequestDetails;
import am.aca.bookingmanagement.dto.partnerdto.register.PartnerRegisterResponseDetails;
import am.aca.bookingmanagement.entity.Partner;
import am.aca.bookingmanagement.exception.PartnerNotFoundException;
import am.aca.bookingmanagement.exception.SomethingWentWrongException;
import am.aca.bookingmanagement.exception.WrongPasswordException;
import am.aca.bookingmanagement.mapper.partnermapper.PartnerMapper;
import am.aca.bookingmanagement.mapper.partnermapper.PartnerMapperImpl;
import am.aca.bookingmanagement.service.partnerservice.PartnerService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PartnerFacadeImpl implements PartnerFacade {

    private final PartnerService partnerService;
    private final PartnerMapper partnerMapper;
    private final PasswordEncoder passwordEncoder;
    private final ValidationChecker validationChecker;

    public PartnerFacadeImpl(final PartnerService partnerService,
                             final PartnerMapperImpl partnerMapper,
                             final PasswordEncoder passwordEncoder,
                             final ValidationChecker validationChecker) {
        this.partnerService = partnerService;
        this.partnerMapper = partnerMapper;
        this.passwordEncoder = passwordEncoder;
        this.validationChecker = validationChecker;
    }

    @Override
    public PartnerRegisterResponseDetails register(final PartnerRegisterRequestDetails request) {
        if (!validationChecker.isPartnerRegistrationValid(request.getName(), request.getEmail(),
                request.getPassword(), request.getAddress(), request.getImageUrl())) {
            throw new SomethingWentWrongException("INVALID_INPUT");
        }
        final Partner partner = partnerService.create(partnerMapper.mapRegisterRequestToEntity(request));
        return partnerMapper.mapEntityToRegisterResponse(partner);
    }

    @Override
    public PartnerLoginResponseDetails login(final PartnerLoginRequestDetails request) {
        if (!validationChecker.isLoginValid(request.getEmail(), request.getPassword())) {
            throw new SomethingWentWrongException("INVALID_EMAIL_OR_PASSWORD");
        }
        final Optional<Partner> byEmail = partnerService.findByEmail(request.getEmail());
        if (byEmail.isEmpty()) {
            throw new PartnerNotFoundException("PARTNER_DOES_NOT_EXIST");
        }
        final boolean passwordsMatch = passwordEncoder.matches(request.getPassword(), byEmail.get().getPassword());
        if (!passwordsMatch) {
            throw new WrongPasswordException("PASSWORDS_MISMATCH");
        }
        return partnerMapper.mapEntityToLoginResponse(byEmail.get());
    }
}