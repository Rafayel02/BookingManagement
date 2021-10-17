package am.aca.bookingmanagement.service.partnerservice;

import am.aca.bookingmanagement.entity.Partner;
import am.aca.bookingmanagement.mapper.partnerMapper.PartnerMapper;
import am.aca.bookingmanagement.repository.PartnerRepository;
import am.aca.bookingmanagement.service.partnerservice.dto.PartnerCreateDetails;
import org.springframework.stereotype.Component;

@Component
public class PartnerServiceImpl implements PartnerService {

    private final PartnerRepository partnerRepository;
    private final PartnerMapper partnerMapper;

    public PartnerServiceImpl(final PartnerRepository partnerRepository, final PartnerMapper partnerMapper) {
        this.partnerRepository = partnerRepository;
        this.partnerMapper = partnerMapper;
    }

    @Override
    public Partner create(final PartnerCreateDetails createDetails) {
        return partnerRepository.save(partnerMapper.mapCreateDetailsToEntity(createDetails));
    }
}
