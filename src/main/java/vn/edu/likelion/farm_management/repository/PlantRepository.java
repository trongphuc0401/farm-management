package vn.edu.likelion.farm_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.likelion.farm_management.entity.PlantEntity;

/**
 * PlantRepository -
 *
 * @param
 * @return
 * @throws
 */
@Repository
public interface PlantRepository extends JpaRepository<PlantEntity,String> {

    boolean existsByName(String name);

}