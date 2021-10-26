package am.aca.bookingmanagement.repository;

import org.springframework.stereotype.Repository;
import am.aca.bookingmanagement.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
}