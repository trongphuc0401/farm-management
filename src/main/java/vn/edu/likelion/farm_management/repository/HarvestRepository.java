package vn.edu.likelion.farm_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.likelion.farm_management.entity.HarvestEntity;

/**
 * HarvestRepository -
 *
 * @param
 * @return
 * @throws
 */
@Repository
public interface HarvestRepository extends JpaRepository<HarvestEntity,String> {
}
