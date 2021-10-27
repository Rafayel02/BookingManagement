package am.aca.bookingmanagement.repository;

import am.aca.bookingmanagement.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Integer> {

    Optional<Activity> findByType(String type);

    @Query(value = "select id from activity where type = ?1", nativeQuery = true)
    Optional<Integer> findActivityIdByType(String type);
}
