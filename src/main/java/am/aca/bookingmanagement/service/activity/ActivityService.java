package am.aca.bookingmanagement.service.activity;

import am.aca.bookingmanagement.repository.ActivityRepository;

import java.util.Optional;

public interface ActivityService {

    Optional<Integer> findActivityIdByType(String type);

}
