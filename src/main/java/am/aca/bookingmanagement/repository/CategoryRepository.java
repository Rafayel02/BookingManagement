package am.aca.bookingmanagement.repository;

import am.aca.bookingmanagement.model.Category;
import am.aca.bookingmanagement.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    public Optional<Category> findByType(final String type);

}
