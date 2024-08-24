package vn.edu.likelion.farm_management.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.edu.likelion.farm_management.common.utils.Convert;
import vn.edu.likelion.farm_management.common.utils.DateTimeUtils;
import vn.edu.likelion.farm_management.dto.response.farm.FarmGeneralResponse;
import vn.edu.likelion.farm_management.dto.response.harvest.HarvestGroupDateResponse;
import vn.edu.likelion.farm_management.dto.response.harvest.HarvestResponse;
import vn.edu.likelion.farm_management.entity.HarvestEntity;
import vn.edu.likelion.farm_management.entity.PlantEntity;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * HarvestRepository -
 *
 * @param
 * @return
 * @throws
 */
@Repository
public interface HarvestRepository extends JpaRepository<HarvestEntity,String> {

    @Query(value = "SELECT DATE(create_at) AS date, " +
            "SUM(yield_total) AS yield_total, " +
            "SUM(price_currently * yield_total) AS price_total " +
            "FROM tbl_harvest " +
            "GROUP BY DATE(create_at) " +
            "ORDER BY DATE(create_at)", nativeQuery = true)
    List<Object[]> getAllMoneyAndYieldGroupDate();

    static void toHarvestGroupDateResponse(HarvestGroupDateResponse harvestGroupDateResponse, Object[] objects) {
        harvestGroupDateResponse.setDate(DateTimeUtils.convertDateToString((Date) objects[0]));

        Double yield_total = (Double) objects[1];
        if (yield_total == null) yield_total = 0.00;
        Double price_total = (Double) objects[2];
        if (price_total == null) price_total = 0.00;
        harvestGroupDateResponse.setYieldTotal(yield_total);
        harvestGroupDateResponse.setPriceTotal(price_total);
    }

    @Query(value = "SELECT h FROM HarvestEntity h WHERE DATE(h.createAt) = :date")
    Page<HarvestEntity> findAllByCreateAt(@Param("date") LocalDate date, Pageable pageable);

    @Query(value = "SELECT h FROM HarvestEntity h WHERE DATE(h.createAt) = :date")
    List<HarvestEntity> findAllByCreateAt(@Param("date") LocalDate date);

    List<HarvestEntity> findAllByOrderByCreateAtAsc();

    @Transactional
    default List<HarvestEntity> saveAll(List<HarvestResponse> harvestResponses) {
        List<HarvestEntity> harvestEntities = harvestResponses.stream()
                .map(this::convertToEntity)
                .collect(Collectors.toList());
        return saveAll(harvestEntities);
    }

    private HarvestEntity convertToEntity(HarvestResponse response) {
        HarvestEntity entity = new HarvestEntity();
        entity.setPlantId(response.getPlantId());
        entity.setPlantName(response.getPlantName());
        entity.setFarmId(response.getFarmId());
        entity.setFarmName(response.getFarmName());
        entity.setDescription(response.getDescription());
        entity.setTotalYield(response.getTotalYield());
        entity.setPriceCurrently(response.getPriceCurrently());
        entity.setIsDeleted(response.getIsDeleted());
        entity.setCreateAt(response.getCreateAt());
        return entity;
    }


}
