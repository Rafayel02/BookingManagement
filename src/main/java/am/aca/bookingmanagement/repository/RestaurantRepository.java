package am.aca.bookingmanagement.repository;

import am.aca.bookingmanagement.model.Restaurant;
import org.springframework.data.repository.CrudRepository;

public interface RestaurantRepository extends CrudRepository<Restaurant, Long> {
}
