package pc_shop.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pc_shop.application.repository.entity.Monitor;
import pc_shop.application.repository.entity.Product;

@Repository
public interface MonitorRepository extends JpaRepository<Monitor, Long> {
}
