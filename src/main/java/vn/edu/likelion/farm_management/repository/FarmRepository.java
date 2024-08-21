package vn.edu.likelion.farm_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.likelion.farm_management.entity.FarmEntity;

/**
 * FarmRepository -
 *
 * @param
 * @return
 * @throws
 */
public interface FarmRepository extends JpaRepository<FarmEntity,String> {
}
