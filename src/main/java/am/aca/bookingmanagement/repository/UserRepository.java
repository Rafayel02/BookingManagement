package am.aca.bookingmanagement.repository;

import am.aca.bookingmanagement.model.Reviews;
import am.aca.bookingmanagement.model.User;
import am.aca.bookingmanagement.model.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
   public Optional<User> findByEmail(final String email);

   public Optional<User> findByFirstName(final String firstname);

   public Optional<User> findByLastName(final String lastname);




}
