package am.aca.bookingmanagement.service.partner;

import am.aca.bookingmanagement.entity.Partner;
import am.aca.bookingmanagement.entity.Review;
import am.aca.bookingmanagement.exception.PartnerAlreadyExistsException;
import am.aca.bookingmanagement.repository.PartnerRepository;
import am.aca.bookingmanagement.service.review.ReviewService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PartnerServiceImpl implements PartnerService {

    private final ReviewService reviewService;
    private final PartnerRepository partnerRepository;

    public PartnerServiceImpl( final ReviewService reviewService, final PartnerRepository partnerRepository) {
        this.partnerRepository = partnerRepository;
        this.reviewService = reviewService;
    }

    @Override
    public Partner create(final Partner partner) {
        final Optional<Partner> byEmail = findByEmail(partner.getEmail());
        if (byEmail.isPresent()) {
            throw new PartnerAlreadyExistsException();
        }
        return partnerRepository.save(partner);
    }

      @Override
    public Optional<Partner> findByEmail(final String email) {
        return partnerRepository.findByEmail(email);
    }

    @Override
    public Optional<Partner> findById(final Long id) {
        return partnerRepository.findById(id);
    }

    @Override
    public void updateAverageRating(final Long id) {
        final Integer currentAverage = partnerRepository.calculateAverage(id);
        partnerRepository.updateRating(currentAverage, id);
    }

    @Override
    public List<Review> getAllReviews(final Long id) {
        return reviewService.getAllReviewsByPartnerId(id);
    }

}