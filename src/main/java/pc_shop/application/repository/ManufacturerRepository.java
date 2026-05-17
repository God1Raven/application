package pc_shop.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pc_shop.application.repository.entity.Manufacturer;

import java.util.Optional;

@Repository
public interface ManufacturerRepository extends JpaRepository<Manufacturer, Long> {

    @Query(value = """
            select * 
            FROM manufacturer m 
            where m.name = :name""", nativeQuery = true)
    Optional<Manufacturer> findByName(@Param("name") String name);
}
