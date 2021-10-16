package am.aca.bookingmanagement.repository;

import am.aca.bookingmanagement.domain.model.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Integer> {
}
