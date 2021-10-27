package am.aca.bookingmanagement.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import am.aca.bookingmanagement.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    Optional<Category> findByType(String type);

    @Query(value = "select id from categories where type = ?1", nativeQuery = true)
    Optional<Integer> findCategoryIdByType(String type);

}