package am.aca.bookingmanagement.facade.partnerfacade;

import am.aca.bookingmanagement.dto.partnerdto.login.PartnerLoginRequestDetails;
import am.aca.bookingmanagement.dto.partnerdto.login.PartnerLoginResponseDetails;
import am.aca.bookingmanagement.dto.partnerdto.register.PartnerRegisterRequestDetails;
import am.aca.bookingmanagement.dto.partnerdto.register.PartnerRegisterResponseDetails;
import am.aca.bookingmanagement.entity.Partner;
import am.aca.bookingmanagement.exception.WrongPasswordException;
import am.aca.bookingmanagement.mapper.partnermapper.PartnerMapper;
import am.aca.bookingmanagement.mapper.partnermapper.PartnerMapperImpl;
import am.aca.bookingmanagement.service.partnerservice.PartnerService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PartnerFacadeImpl implements PartnerFacade {

    private final PartnerService partnerService;
    private final PartnerMapper partnerMapper;
    private final PasswordEncoder passwordEncoder;

    public PartnerFacadeImpl(final PartnerService partnerService,
                             final PartnerMapperImpl partnerMapper,
                             final PasswordEncoder passwordEncoder) {
        this.partnerService = partnerService;
        this.partnerMapper = partnerMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public PartnerRegisterResponseDetails register(final PartnerRegisterRequestDetails request) {
        final Partner partner = partnerService.create(partnerMapper.mapRegisterRequestToEntity(request));
        /*TODO switching to token facade, to generate token and save in db
            (rather to do with transactions of saving user and token)*/
        //TODO validation checks, if something doesn't match always throw SOMETHING_WENT_WRONG_EXCEPTION
        return partnerMapper.mapEntityToRegisterResponse(partner);
    }

    @Override
    public PartnerLoginResponseDetails login(final PartnerLoginRequestDetails request) {
        /*TODO getting token from request body, switching into
           token facade (to check token in db after some logic with token and restart it if needed)*/
        //TODO validation checks, if something doesn't match always throw SOMETHING_WENT_WRONG_EXCEPTION
        final Partner byEmail = partnerService.findByEmail(request.getEmail());
        final boolean passwordsMatch = passwordEncoder.matches(request.getPassword(), byEmail.getPassword());

        if (!passwordsMatch){
            throw new WrongPasswordException("PASSWORDS_MISMATCH");
        }

        return partnerMapper.mapEntityToLoginResponse(byEmail);
    }

}