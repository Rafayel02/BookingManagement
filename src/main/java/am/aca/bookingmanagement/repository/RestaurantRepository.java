package am.aca.bookingmanagement.repository;

import am.aca.bookingmanagement.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    public Optional<Restaurant> findByName(final String name);

    public Optional<Restaurant> findByEmail(final String email);

    public Optional<Restaurant>  findByAddress(final String address);

}
