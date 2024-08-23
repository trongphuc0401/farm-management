package vn.edu.likelion.farm_management.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.edu.likelion.farm_management.common.utils.Convert;
import vn.edu.likelion.farm_management.dto.response.farm.AllFarmGeneralResponse;
import vn.edu.likelion.farm_management.dto.response.farm.FarmGeneralResponse;
import vn.edu.likelion.farm_management.entity.FarmEntity;


import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import java.util.Optional;


/**
 * FarmRepository -
 *
 * @param
 * @return
 * @throws
 */

@Repository
public interface FarmRepository extends JpaRepository<FarmEntity, String> {
    // Query native reference
    @Query(value = "SELECT " +
            "tp.name as plant_name, " + // Tên cây trồng trên farm
            "ttp.name as type_plant_name, " + // Tên loại cây trồng trên farm
            "SUM(tp.area) as area_planted," + // Tổng diện tích cây đã trồng trên farm
            "COUNT(*) AS harvestable_plant_count, " + // Đếm số lượng cây có thể thu hoạch
            "MIN(tp.date_fruiting_stage_finish) as date_harvest  " + // Ngày có thể thu hoạch
            "FROM tbl_plant tp " +
            "JOIN tbl_type_plant ttp ON tp.type_plant_id = ttp.id " +
            "WHERE tp.farm_id = :farm_id " +
            "GROUP BY ttp.name, tp.name " +
            "ORDER BY harvestable_plant_count DESC", nativeQuery = true)
    List<Object[]> findFarmInformationToFarmResponse(@Param("farm_id") String farmId);

    static void toFarmGeneralResponse(FarmGeneralResponse farmGeneralResponse, Object[] objects) {
        farmGeneralResponse.setPlantName((String) objects[0]);
        farmGeneralResponse.setTypePlantName((String) objects[1]);
        farmGeneralResponse.setAreaPlanted((Double) objects[2]);
        farmGeneralResponse.setHarvestablePlantCount(Convert.longToInterger((Long) objects[3]));
        farmGeneralResponse.setDateHarvest(Convert.timeStampToLocalDateTime((Timestamp) objects[4]));
    }

    @Query(value = "SELECT " +
            "SUM(tp.area) as area_planted, " +   // Tổng diện tích cây trồng
            "(SELECT SUM(area) FROM tbl_farm) AS area, " +  // Tổng diện tích của tất cả các farm
            "SUM(tp.expected_yield) as yield_total, " +   // Tổng sản lượng của từng cây trồng
            "SUM(tp.price * tp.expected_yield) as price_total " +    // Tổng giá trị tiền từ cây trồng
            "FROM tbl_plant tp " +
            "JOIN tbl_farm f ON tp.farm_id = f.id ", // Giả sử có bảng `tbl_farm` với thông tin diện tích farm
            nativeQuery = true)
    List<Object[]> getTotalPlantedAreaAllFarm();


    static void toAllFarmGeneralResponse(AllFarmGeneralResponse allFarmGeneralResponse, Object[] objects) {
        allFarmGeneralResponse.setAreaPlanted((Double) objects[0]);
        allFarmGeneralResponse.setArea((Double) objects[1]);
        allFarmGeneralResponse.setYieldTotal((Double) objects[2]);
        allFarmGeneralResponse.setPriceTotal((Double) objects[3]);
    }



}
