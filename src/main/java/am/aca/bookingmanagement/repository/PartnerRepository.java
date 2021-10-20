package am.aca.bookingmanagement.repository;

import am.aca.bookingmanagement.entity.Partner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PartnerRepository extends JpaRepository<Partner, Long> {

    Optional<Partner> findByName(String name);

    Optional<Partner> findByEmail(String email);

    Optional<Partner> findByAddress(String address);

    Optional<Partner> findByUuid(String uuid);

}
