package am.aca.bookingmanagement.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import am.aca.bookingmanagement.entity.Partner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

@Repository
public interface FilterRepository extends JpaRepository<Partner, Long>, JpaSpecificationExecutor<Partner> {

    @Query(value = "select distinct id, name, email, password, longitude, latitude, image_url, address, rating from partners inner join partners_categories pc on partners.id = " +
            "pc.partner_id where pc.type_id in (:list)", nativeQuery = true)
    List<Partner> findByCategory(@Param("list") List<Integer> list);

    @Query(value = "select distinct id, name, email, password, longitude, latitude, image_url, address, rating from partners inner join partners_activities pa on partners.id = " +
            "pa.partner_id where pa.type_id in (:list)", nativeQuery = true)
    List<Partner> findByActivity(@Param("list") List<Integer> list);

    @Query(value = "select distinct id, name, email, password, longitude, latitude, image_url, address, rating " +
            "from (select distinct id, name, email, password, longitude, latitude, image_url, address, rating " +
            "      from partners p inner join (select distinct partner_id from partners_categories pc where pc.type_id in (:listCat)) pc on p.id = pc.partner_id) pm " +
            "         inner join (select distinct partner_id from partners_activities pa where pa.type_id in (:listAct)) pa on pa.partner_id = pm.id;", nativeQuery = true)
    List<Partner> findByCategoryAndActivity(@Param("listCat") List<Integer> listCat,
                                            @Param("listAct") List<Integer> listAct);

    @Query(value = "select * from partners where partners.id in :list", nativeQuery = true)
    List<Partner> findById(@Param("list") List<Integer> partnerSIdList);
}