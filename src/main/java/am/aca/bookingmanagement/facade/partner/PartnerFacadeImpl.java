package am.aca.bookingmanagement.facade.partner;

import am.aca.bookingmanagement.checker.ValidationChecker;
import am.aca.bookingmanagement.dto.filter.FilteredPartnerResponseDetails;
import am.aca.bookingmanagement.dto.partner.login.PartnerLoginRequestDetails;
import am.aca.bookingmanagement.dto.partner.login.PartnerLoginResponseDetails;
import am.aca.bookingmanagement.dto.partner.register.PartnerRegisterRequestDetails;
import am.aca.bookingmanagement.dto.partner.register.PartnerRegisterResponseDetails;
import am.aca.bookingmanagement.entity.Partner;
import am.aca.bookingmanagement.entity.Review;
import am.aca.bookingmanagement.exception.*;
import am.aca.bookingmanagement.mapper.partner.PartnerMapper;
import am.aca.bookingmanagement.service.activity.ActivityService;
import am.aca.bookingmanagement.service.category.CategoryService;
import am.aca.bookingmanagement.service.partner.PartnerService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PartnerFacadeImpl implements PartnerFacade {

    private final PartnerMapper partnerMapper;
    private final PartnerService partnerService;
    private final CategoryService categoryService;
    private final PasswordEncoder passwordEncoder;
    private final ActivityService activityService;
    private final ValidationChecker validationChecker;

    public PartnerFacadeImpl(final PartnerMapper partnerMapper,
                             final PartnerService partnerService,
                             final CategoryService categoryService,
                             final PasswordEncoder passwordEncoder,
                             final ActivityService activityService,
                             final ValidationChecker validationChecker) {
        this.partnerMapper = partnerMapper;
        this.partnerService = partnerService;
        this.categoryService = categoryService;
        this.passwordEncoder = passwordEncoder;
        this.activityService = activityService;
        this.validationChecker = validationChecker;
    }

    @Override
    public PartnerRegisterResponseDetails register(final PartnerRegisterRequestDetails request) {
        if (!validationChecker.isPartnerRegistrationValid(request.getName(), request.getEmail(),
                request.getPassword(), request.getAddress())) {
            throw new SomethingWentWrongException();
        }
        final Partner partner = partnerService.create(partnerMapper.mapRegisterRequestToEntity(request));
        addPartnerCategory(partner.getId(), request.getPartnerCategories());
        addPartnerActivity(partner.getId(), request.getPartnerActivities());
        return partnerMapper.mapEntityToRegisterResponse(partner);
    }

    private void addPartnerActivity(final Long id, final List<String> partnerActivities) {
        for (final String partnerActivity : partnerActivities) {
            final Optional<Integer> activityId = activityService.findActivityIdByType(partnerActivity);
            if (activityId.isEmpty()){
                throw new ActivityNotFoundException();
            }
            partnerService.createPartnersActivities(id, activityId.get());
        }
    }

    private void addPartnerCategory(final Long id, final List<String> partnerCategories) {
        for (final String category : partnerCategories) {
            final Optional<Integer> categoryId = categoryService.findCategoryIdByType(category);
            if (categoryId.isEmpty()) {
                throw new CategoryNotFoundException();
            }
            partnerService.createPartnersCategories(id, categoryId.get());
        }
    }

    @Override
    public PartnerLoginResponseDetails login(final PartnerLoginRequestDetails request) {
        if (!validationChecker.isLoginValid(request.getEmail(), request.getPassword())) {
            throw new SomethingWentWrongException();
        }
        final Optional<Partner> byEmail = partnerService.findByEmail(request.getEmail());
        if (byEmail.isEmpty()) {
            throw new PartnerNotFoundException();
        }
//        final boolean doPasswordsMatch = passwordEncoder.matches(request.getPassword(), byEmail.get().getPassword());
//        if (!doPasswordsMatch) {
//            throw new WrongPasswordException();
//        }
        return partnerMapper.mapEntityToLoginResponse(byEmail.get());
    }

    @Override
    public void updateAverageRating(final Long id) {
        partnerService.updateAverageRating(id);
    }

    @Override
    public List<Review> getAllReviews(final Long id) {
        return partnerService.getAllReviews(id);
    }

    @Override
    public FilteredPartnerResponseDetails getPartner(final Long id) {
        final Optional<Partner> partner = partnerService.findById(id);
        if (partner.isEmpty()) {
            throw new PartnerNotFoundException();
        }
        return partnerMapper.mapEntityToFilteredPartner(partner.get());
    }

}