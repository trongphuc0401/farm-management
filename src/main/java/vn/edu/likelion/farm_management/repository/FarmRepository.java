package vn.edu.likelion.farm_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.likelion.farm_management.entity.FarmEntity;



/**
 * FarmRepository -
 *
 * @param
 * @return
 * @throws
 */

@Repository
public interface FarmRepository extends JpaRepository<FarmEntity,String> {
}
