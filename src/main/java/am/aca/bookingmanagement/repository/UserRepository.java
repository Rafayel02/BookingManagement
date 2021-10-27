package am.aca.bookingmanagement.repository;

import am.aca.bookingmanagement.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    Optional<User> findByFirstName(String firstname);

    Optional<User> findByLastName(String lastname);

    Optional<User> findByUuid(String uuid);

    @Query(value = "select id from users u where u.uuid = ?1", nativeQuery = true)
    Long findIdByUuid(String uuid);


}