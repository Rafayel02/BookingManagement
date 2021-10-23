package am.aca.bookingmanagement.repository;

import am.aca.bookingmanagement.entity.Partner;
import am.aca.bookingmanagement.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface PartnerRepository extends JpaRepository<Partner, Long> {

    Optional<Partner> findByName(String name);

    Optional<Partner> findByEmail(String email);

    Optional<Partner> findByAddress(String address);

    Optional<Partner> findByUuid(String uuid);

    @Query(value = "select id from partners   where uuid = ?1", nativeQuery = true)
    Long findIdByUuid(String uuid);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update partners set rating = ?1 where id = ?2", nativeQuery = true)
     Integer  setPartnerRating(Integer rating, Long id);

}
