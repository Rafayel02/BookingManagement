package am.aca.bookingmanagement.repository;

import am.aca.bookingmanagement.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    public Optional<Restaurant> getByName(final String name);

    public Optional<Restaurant> getByEmail(final String email);

    public Optional<Restaurant>  getByAddress(final String address);

}
