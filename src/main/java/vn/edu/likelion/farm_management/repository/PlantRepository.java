package vn.edu.likelion.farm_management.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.edu.likelion.farm_management.dto.response.plant.PaginatePlantResponse;
import vn.edu.likelion.farm_management.entity.PlantEntity;

import java.util.List;

/**
 * PlantRepository -
 *
 * @param
 * @return
 * @throws
 */
@Repository
public interface PlantRepository extends JpaRepository<PlantEntity,String>, PagingAndSortingRepository<PlantEntity, String> {

    boolean existsByName(String name);

    // Query native reference
    @Query(value =  "SELECT tp.id,tp.name FROM tbl_plant tp" +
                    " WHERE LOWER(tp.name) LIKE LOWER(CONCAT(:searchText, '%'))", nativeQuery = true)
    Page<Object[]> findPlantBySearchText1(@Param("searchText") String searchText , Pageable pageable);

    @Query(value = "SELECT tp FROM PlantEntity tp WHERE LOWER(tp.name) LIKE LOWER(CONCAT(:searchText, '%'))")
    Page<PlantEntity> findPlantBySearchText(@Param("searchText") String searchText, Pageable pageable);


    List<PlantEntity> findPlantByFarmId(String farmId);


}
