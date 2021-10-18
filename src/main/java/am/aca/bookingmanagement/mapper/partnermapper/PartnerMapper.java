package am.aca.bookingmanagement.mapper.partnermapper;

import am.aca.bookingmanagement.dto.partnerdto.PartnerLoginResponseDetails;
import am.aca.bookingmanagement.dto.partnerdto.PartnerRegisterRequestDetails;
import am.aca.bookingmanagement.dto.partnerdto.PartnerRegisterResponseDetails;
import am.aca.bookingmanagement.entity.Partner;

public interface PartnerMapper {

    Partner mapRegisterRequestToEntity(PartnerRegisterRequestDetails partnerRegisterRequestDetails);

    PartnerRegisterResponseDetails mapEntityToRegisterResponse(Partner partner);

    PartnerLoginResponseDetails mapEntityToLoginResponse(Partner partner);
}
