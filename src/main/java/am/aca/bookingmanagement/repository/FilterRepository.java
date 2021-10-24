package am.aca.bookingmanagement.repository;

import am.aca.bookingmanagement.dto.filterdto.FilterRequestDetails;
import am.aca.bookingmanagement.entity.Partner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FilterRepository extends JpaRepository<Partner, Long> {

    @Query(value = "select * from partners", nativeQuery = true)
    Optional<List<Partner>> findAll(FilterRequestDetails filterRequestDetails);

    @Query(value = "select * from partners inner join partners_categories pc on partners.id = " +
            "pc.partner_id where pc.type_id in (:list)", nativeQuery = true)
    Optional<List<Partner>> findByCategory(@Param("list") List<Integer> list);


    @Query(value = "select * from partners where rating >= ?1", nativeQuery = true)
    Optional<List<Partner>> findByReview(Integer rating);

    @Query(value = "select * from partners inner join partners_categories pc on partners.id = " +
            "pc.partner_id where pc.type_id in (:list) and partners.rating >= :rating ", nativeQuery = true)
    Optional<List<Partner>> findByCategoryAndReview(@Param("list") List<Integer> list, @Param("rating") Integer rating);


}