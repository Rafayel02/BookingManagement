package am.aca.bookingmanagement.repository;

import am.aca.bookingmanagement.domain.model.Reviews;
import org.springframework.data.repository.CrudRepository;

public interface ReviewsRepository extends CrudRepository<Reviews, Long> {
}
