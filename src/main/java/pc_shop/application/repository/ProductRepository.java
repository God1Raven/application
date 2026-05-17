package pc_shop.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pc_shop.application.repository.entity.Product;
import pc_shop.application.shared.ProductType;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByProductType(ProductType productType);

    @Query("SELECT p FROM Product p LEFT JOIN FETCH p.manufacturer")
    List<Product> findAllWithManufacturer();
}
