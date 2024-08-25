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

import java.time.LocalDateTime;
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
                    " WHERE LOWER(tp.name) LIKE LOWER(CONCAT(:searchText, '%')) AND tp.isDeleted = 0", nativeQuery = true)
    Page<Object[]> findPlantBySearchText1(@Param("searchText") String searchText , Pageable pageable);

    @Query(value = "SELECT tp FROM PlantEntity tp WHERE LOWER(tp.name) LIKE LOWER(CONCAT('%',:searchText, '%')) AND tp.isDeleted = 0")
    Page<PlantEntity> findPlantBySearchText(@Param("searchText") String searchText, Pageable pageable);

    @Query(value = "SELECT tp FROM PlantEntity tp WHERE tp.typePlant.id = :typePlantId AND tp.farmId IS NULL AND tp.isDeleted = 0")
    Page<PlantEntity> findAllByTypePlantId(@Param("typePlantId") String typePlantId, Pageable pageable);


    @Query("SELECT p FROM PlantEntity p WHERE p.isDeleted = 0")
    Page<PlantEntity> findAllNonDeletedPlants(@Param("") Pageable pageable);

    List<PlantEntity> findPlantByFarmId(String farmId);

    @Query("SELECT p FROM PlantEntity p WHERE p.dateFruitingStageFinish <= :date AND p.isDeleted = 0 ORDER BY p.dateFruitingStageFinish ASC")
    List<PlantEntity> findReadyToHarvestPlants(@Param("date") LocalDateTime date);

    @Query("SELECT pe FROM PlantEntity pe WHERE pe.id IN :ids AND pe.dateFruitingStageFinish <= :currentDate AND pe.isDeleted = 0 ORDER BY pe.dateFruitingStageFinish ASC")
    List<PlantEntity> findPlantsReadyForHarvest(@Param("ids") List<String> plantIds, @Param("currentDate") LocalDateTime date);


}
