package am.aca.bookingmanagement.repository;

import am.aca.bookingmanagement.entity.Partner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface FilterRepository extends JpaRepository<Partner, Long>, JpaSpecificationExecutor<Partner> {

    @Query(value = "select * from partners inner join partners_categories pc on partners.id = " +
            "pc.partner_id where pc.type_id in (:list)", nativeQuery = true)
    Set<Partner> findByCategory(@Param("list") List<Integer> list);

    @Query(value = "select * from partners inner join partners_activities pa on partners.id = " +
            "pa.partner_id where pa.type_id in (:list)", nativeQuery = true)
    Set<Partner> findByActivity(@Param("list") List<Integer> list);

    @Query(value = "select distinct * " +
            "from (select distinct * " +
            "      from partners p inner join (select * from partners_categories pc where pc.type_id in ?1) pc on p.id = pc.partner_id) pm " +
            "         inner join (select * from partners_activities pa where pa.type_id in ?2) pa on pa.partner_id = pm.id;", nativeQuery = true)

//    @Query(value = "select * from  partners " +
//            "inner join (select * from partners_categories pc where pc.type_id in :listCat) pc on partners.id = pc.partner_id " +
//            "inner join (select * from partners_activities pa where pa.type_id in :listAct) pa on partners.id = pa.partner_id ",
//            nativeQuery = true)
    Set<Partner> findByCategoryAndActivity(@Param("listCat") List<Integer> listCat,
                                           @Param("listAct") List<Integer> listAct);

    @Query(value = "select * from partners where partners.id = :list", nativeQuery = true)
    Set<Partner> findById(@Param("list") List<Integer> partnerSIdList);
}