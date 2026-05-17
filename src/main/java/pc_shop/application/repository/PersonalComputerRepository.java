package pc_shop.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pc_shop.application.repository.entity.PersonalComputer;

@Repository
public interface PersonalComputerRepository extends JpaRepository<PersonalComputer, Long> {
}
