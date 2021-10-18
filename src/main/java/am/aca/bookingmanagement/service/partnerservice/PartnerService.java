package am.aca.bookingmanagement.service.partnerservice;

import am.aca.bookingmanagement.entity.Partner;

public interface PartnerService {

    Partner create(Partner partner);

    Partner findByEmail(String email);
}