package am.aca.bookingmanagement.repository;

import am.aca.bookingmanagement.domain.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    @Query(value = "select * from users where email = ?1", nativeQuery = true)
     public User getUserByEmail(final String email);

}
