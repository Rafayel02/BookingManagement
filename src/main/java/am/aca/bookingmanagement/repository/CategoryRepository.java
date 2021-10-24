package am.aca.bookingmanagement.repository;

import am.aca.bookingmanagement.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    Optional<Category> findByType(String type);

    @Query(value = "select id from categories where type = ?1", nativeQuery = true)
    Optional<Integer> findCategoryIdByType(String type);
}