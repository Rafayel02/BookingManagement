package am.aca.bookingmanagement.facade.partnerfacade;

import am.aca.bookingmanagement.dto.partnerdto.login.PartnerLoginRequestDetails;
import am.aca.bookingmanagement.dto.partnerdto.login.PartnerLoginResponseDetails;
import am.aca.bookingmanagement.dto.partnerdto.register.PartnerRegisterRequestDetails;
import am.aca.bookingmanagement.dto.partnerdto.register.PartnerRegisterResponseDetails;
import am.aca.bookingmanagement.entity.Partner;
import am.aca.bookingmanagement.mapper.partnermapper.PartnerMapper;
import am.aca.bookingmanagement.mapper.partnermapper.PartnerMapperImpl;
import am.aca.bookingmanagement.service.partnerservice.PartnerService;
import org.springframework.stereotype.Component;

@Component
public class PartnerFacadeImpl implements PartnerFacade {

    private final PartnerService partnerService;
    private final PartnerMapper partnerMapper;

    public PartnerFacadeImpl(final PartnerService partnerService, final PartnerMapperImpl partnerMapper) {
        this.partnerService = partnerService;
        this.partnerMapper = partnerMapper;
    }

    @Override
    public PartnerRegisterResponseDetails register(final PartnerRegisterRequestDetails request) {
        final Partner partner = partnerService.create(partnerMapper.mapRegisterRequestToEntity(request));
        /*TODO switching to token facade, to generate token and save in db
            (rather to do with transactions of saving user and token)*/
        return partnerMapper.mapEntityToRegisterResponse(partner);
    }

    @Override
    public PartnerLoginResponseDetails login(final PartnerLoginRequestDetails request) {
        /*TODO getting token from request body, switching into
           token facade (to check token in db after some logic with token and restart it if needed)*/

        return partnerMapper.mapEntityToLoginResponse(partnerService.findByEmail(request.getEmail()));
    }

}