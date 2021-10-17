package am.aca.bookingmanagement.repository;

import am.aca.bookingmanagement.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(final String email);

    Optional<User> findByFirstName(final String firstname);

    Optional<User> findByLastName(final String lastname);
}