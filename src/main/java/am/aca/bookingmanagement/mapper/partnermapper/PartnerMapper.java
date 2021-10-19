package am.aca.bookingmanagement.mapper.partnermapper;

import am.aca.bookingmanagement.dto.partnerdto.login.PartnerLoginResponseDetails;
import am.aca.bookingmanagement.dto.partnerdto.register.PartnerRegisterRequestDetails;
import am.aca.bookingmanagement.dto.partnerdto.register.PartnerRegisterResponseDetails;
import am.aca.bookingmanagement.entity.Partner;

public interface PartnerMapper {

    Partner mapRegisterRequestToEntity(PartnerRegisterRequestDetails partnerRegisterRequestDetails);

    PartnerRegisterResponseDetails mapEntityToRegisterResponse(Partner partner);

    PartnerLoginResponseDetails mapEntityToLoginResponse(Partner partner);
}
