package am.aca.bookingmanagement.service.partnerservice;

import am.aca.bookingmanagement.entity.Partner;
import am.aca.bookingmanagement.exception.PartnerAlreadyExistsException;
import am.aca.bookingmanagement.repository.PartnerRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PartnerServiceImpl implements PartnerService {

    private final PartnerRepository partnerRepository;

    public PartnerServiceImpl(final PartnerRepository partnerRepository) {
        this.partnerRepository = partnerRepository;
    }

    @Override
    public Partner create(final Partner partner) {
        final Optional<Partner> byEmail = findByEmail(partner.getEmail());
        if(byEmail.isPresent()) {
            throw new PartnerAlreadyExistsException("PARTNER_WITH_EMAIL_" + partner.getEmail() + "_ALREADY_EXISTS");
        }
        return partnerRepository.save(partner);
    }

    @Override
    public Optional<Partner> findByEmail(final String email) {
        return partnerRepository.findByEmail(email);
    }

}
