package am.aca.bookingmanagement.mapper.partner;

import am.aca.bookingmanagement.entity.Partner;
import am.aca.bookingmanagement.dto.partner.login.PartnerLoginResponseDetails;
import am.aca.bookingmanagement.dto.partner.register.PartnerRegisterRequestDetails;
import am.aca.bookingmanagement.dto.partner.register.PartnerRegisterResponseDetails;

public interface PartnerMapper {

    Partner mapRegisterRequestToEntity(PartnerRegisterRequestDetails request);

    PartnerRegisterResponseDetails mapEntityToRegisterResponse(Partner partner);

    PartnerLoginResponseDetails mapEntityToLoginResponse(Partner partner);

}