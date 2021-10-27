package am.aca.bookingmanagement.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import am.aca.bookingmanagement.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

}