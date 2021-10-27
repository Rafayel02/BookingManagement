package am.aca.bookingmanagement.repository;

import am.aca.bookingmanagement.entity.Partner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface PartnerRepository extends JpaRepository<Partner, Long> {

    Optional<Partner> findByName(String name);

    Optional<Partner> findByEmail(String email);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update partners set rating = ?1 where id = ?2", nativeQuery = true)
    Integer updateRating(Integer rating, Long id);

    @Query(value = "SELECT AVG(rating) from reviews where partner_id = ?1", nativeQuery = true)
    Integer calculateAverage(Long id);

}