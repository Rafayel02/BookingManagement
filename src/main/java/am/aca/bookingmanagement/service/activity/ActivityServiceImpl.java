package am.aca.bookingmanagement.service.activity;

import am.aca.bookingmanagement.repository.ActivityRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ActivityServiceImpl implements ActivityService{

    private final ActivityRepository activityRepository;

    public ActivityServiceImpl(final ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    @Override
    public Optional<Integer> findActivityIdByType(final String type) {
        return activityRepository.findActivityIdByType(type);
    }
}
