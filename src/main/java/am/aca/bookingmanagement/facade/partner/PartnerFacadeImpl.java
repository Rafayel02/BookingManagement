package am.aca.bookingmanagement.facade.partner;

import am.aca.bookingmanagement.entity.Review;
import org.springframework.stereotype.Component;
import am.aca.bookingmanagement.entity.Partner;
import am.aca.bookingmanagement.checker.ValidationChecker;
import am.aca.bookingmanagement.exception.WrongPasswordException;
import am.aca.bookingmanagement.exception.PartnerNotFoundException;
import am.aca.bookingmanagement.mapper.partner.PartnerMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import am.aca.bookingmanagement.service.partner.PartnerService;
import am.aca.bookingmanagement.exception.SomethingWentWrongException;
import am.aca.bookingmanagement.dto.partner.login.PartnerLoginRequestDetails;
import am.aca.bookingmanagement.dto.partner.login.PartnerLoginResponseDetails;
import am.aca.bookingmanagement.dto.partner.register.PartnerRegisterRequestDetails;
import am.aca.bookingmanagement.dto.partner.register.PartnerRegisterResponseDetails;

import java.util.List;
import java.util.Optional;

@Component
public class PartnerFacadeImpl implements PartnerFacade {

    private final PartnerMapper partnerMapper;
    private final PartnerService partnerService;
    private final PasswordEncoder passwordEncoder;
    private final ValidationChecker validationChecker;

    public PartnerFacadeImpl(final PartnerMapper partnerMapper,
                             final PartnerService partnerService,
                             final PasswordEncoder passwordEncoder,
                             final ValidationChecker validationChecker) {
        this.partnerMapper = partnerMapper;
        this.partnerService = partnerService;
        this.passwordEncoder = passwordEncoder;
        this.validationChecker = validationChecker;
    }

    @Override
    public PartnerRegisterResponseDetails register(final PartnerRegisterRequestDetails request) {
//        if (!validationChecker.isPartnerRegistrationValid(request.getName(), request.getEmail(),
//                request.getPassword(), request.getAddress(), request.getImageUrl())) {
//            throw new SomethingWentWrongException();
//        }
        final Partner partner = partnerService.create(partnerMapper.mapRegisterRequestToEntity(request));
        return partnerMapper.mapEntityToRegisterResponse(partner);
    }

    @Override
    public PartnerLoginResponseDetails login(final PartnerLoginRequestDetails request) {
        if (!validationChecker.isLoginValid(request.getEmail(), request.getPassword())) {
            throw new SomethingWentWrongException();
        }
        final Optional<Partner> byEmail = partnerService.findByEmail(request.getEmail());
        if (byEmail.isEmpty()) {
            throw new PartnerNotFoundException();
        }
        final boolean doPasswordsMatch = passwordEncoder.matches(request.getPassword(), byEmail.get().getPassword());
        if (!doPasswordsMatch) {
            throw new WrongPasswordException();
        }
        return partnerMapper.mapEntityToLoginResponse(byEmail.get());
    }

    @Override
    public void updateAverageRating(final Long id) {
        partnerService.updateAverageRating(id);
    }

    @Override
    public List<Review> getAllReviews(final Long id) {
        return partnerService.getAllReviews(id);
    }

}