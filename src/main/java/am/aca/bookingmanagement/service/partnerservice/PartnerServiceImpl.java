package am.aca.bookingmanagement.service.partnerservice;

import am.aca.bookingmanagement.entity.Partner;
import am.aca.bookingmanagement.exception.UserNotFoundException;
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
        return partnerRepository.save(partner);
    }

    @Override
    public Partner findByEmail(final String email) {
        final Optional<Partner> byEmail = partnerRepository.findByEmail(email);

        if (byEmail.isEmpty()) {
            throw new UserNotFoundException("User not found");
        }
        return byEmail.get();
    }

}
