package am.aca.bookingmanagement.repository;

import am.aca.bookingmanagement.model.Reviews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewsRepository extends JpaRepository<Reviews,Long> {
}