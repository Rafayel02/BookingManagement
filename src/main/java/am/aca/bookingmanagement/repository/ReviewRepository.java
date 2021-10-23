package am.aca.bookingmanagement.repository;

import am.aca.bookingmanagement.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Long> {
    @Query(value = "SELECT AVG(rating) from reviews  where partner_id = ?1", nativeQuery = true)
    public Integer calculateAverage(Long id);
}
