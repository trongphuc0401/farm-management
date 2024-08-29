package vn.edu.likelion.farm_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.likelion.farm_management.entity.TypePlantEntity;

/**
 * TypePlantRepository -
 *
 * @param
 * @return
 * @throws
 */
@Repository
public interface TypePlantRepository extends JpaRepository<TypePlantEntity,String> {

}
