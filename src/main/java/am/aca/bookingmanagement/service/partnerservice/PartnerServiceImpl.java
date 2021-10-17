package am.aca.bookingmanagement.service.partnerservice;

import am.aca.bookingmanagement.entity.Restaurant;
import am.aca.bookingmanagement.mapper.partnerMapper.PartnerMapper;
import am.aca.bookingmanagement.repository.RestaurantRepository;
import am.aca.bookingmanagement.service.partnerservice.dto.PartnerCreateDetails;
import org.springframework.stereotype.Component;

@Component
public class PartnerServiceImpl implements PartnerService {

    private final RestaurantRepository restaurantRepository;
    private final PartnerMapper partnerMapper;

    public PartnerServiceImpl(final RestaurantRepository restaurantRepository, final PartnerMapper partnerMapper) {
        this.restaurantRepository = restaurantRepository;
        this.partnerMapper = partnerMapper;
    }

    @Override
    public Restaurant create(final PartnerCreateDetails createDetails) {
        return restaurantRepository.save(partnerMapper.mapCreateDetailsToEntity(createDetails));
    }
}
