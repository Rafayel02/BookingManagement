package am.aca.bookingmanagement.repository;

import org.springframework.stereotype.Repository;
import am.aca.bookingmanagement.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    Optional<Review> findReviewByUser_IdAndPartner_Id(Long userId, Long partnerId);
}