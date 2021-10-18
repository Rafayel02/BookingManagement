package am.aca.bookingmanagement.facade.partnerfacade;

import am.aca.bookingmanagement.entity.Partner;
import am.aca.bookingmanagement.facade.partnerfacade.partnerlogindto.PartnerLoginRequestDetails;
import am.aca.bookingmanagement.facade.partnerfacade.partnerlogindto.PartnerLoginResponseDetails;
import am.aca.bookingmanagement.facade.partnerfacade.partnerregisterdto.PartnerRegisterRequestDetails;
import am.aca.bookingmanagement.facade.partnerfacade.partnerregisterdto.PartnerRegisterResponseDetails;
import am.aca.bookingmanagement.mapper.partnerMapper.PartnerMapper;
import am.aca.bookingmanagement.mapper.partnerMapper.PartnerMapperImpl;
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
        final Partner partner = partnerService.create(partnerMapper.mapRequestToDetails(request));
        /*TODO switching to token facade, to generate token and save in db
            (rather to do with transactions of saving user and token)*/
        return partnerMapper.mapEntityToResponse(partner);
    }

    @Override
    public PartnerLoginResponseDetails login(final PartnerLoginRequestDetails partnerLoginRequestDetails) {
        /*TODO getting token from request body, switching into
           token facade (to check token in db after some logic with token and restart it if needed)*/
        return null;
    }

}