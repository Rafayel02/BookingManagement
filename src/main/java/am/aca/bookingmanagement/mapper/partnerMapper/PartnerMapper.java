package am.aca.bookingmanagement.mapper.partnerMapper;

import am.aca.bookingmanagement.entity.Partner;
import am.aca.bookingmanagement.facade.partnerfacade.partnerregisterdto.PartnerRegisterRequestDetails;
import am.aca.bookingmanagement.facade.partnerfacade.partnerregisterdto.PartnerRegisterResponseDetails;
import am.aca.bookingmanagement.service.partnerservice.dto.PartnerCreateDetails;

public interface PartnerMapper {

    PartnerCreateDetails mapRequestToDetails(PartnerRegisterRequestDetails partnerRegisterRequestDetails);

    PartnerRegisterResponseDetails mapEntityToResponse (Partner partner);

    Partner mapCreateDetailsToEntity(PartnerCreateDetails partnerCreateDetails);
}
