package am.aca.bookingmanagement.repository;

import am.aca.bookingmanagement.domain.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    Optional<Restaurant> findByName(String name);

    Optional<Restaurant> findByEmail(String email);

    Optional<Restaurant> findByAddress(String address);

}
