package vn.edu.likelion.farm_management.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.edu.likelion.farm_management.common.utils.Convert;
import vn.edu.likelion.farm_management.dto.response.dashboard.HarvestReport;
import vn.edu.likelion.farm_management.dto.response.dashboard.MonthlyPlantHarvestSummaryDTO;
import vn.edu.likelion.farm_management.dto.response.farm.AllFarmGeneralResponse;
import vn.edu.likelion.farm_management.dto.response.farm.FarmGeneralResponse;
import vn.edu.likelion.farm_management.entity.FarmEntity;
import vn.edu.likelion.farm_management.entity.PlantEntity;


import java.sql.Timestamp;
import java.util.List;


/**
 * FarmRepository -
 *
 * @param
 * @return
 * @throws
 */

@Repository
public interface FarmRepository extends JpaRepository<FarmEntity, String> {

    @Query("SELECT p FROM FarmEntity p WHERE p.isDeleted = 0")
    List<FarmEntity> findAllNonDeletedFarms();

    // Query native reference
    @Query(value = "SELECT " + "tp.name as plant_name, " + // Tên cây trồng trên farm
            "ttp.name as type_plant_name, " + // Tên loại cây trồng trên farm
            "SUM(tp.area) as area_planted," + // Tổng diện tích cây đã trồng trên farm
            "COUNT(CASE WHEN tp.date_fruiting_stage_finish <= CURRENT_DATE THEN 1 ELSE NULL END) AS harvestable_plant_count, " +
            "MIN(tp.date_fruiting_stage_finish) as date_harvest  " + // Ngày có thể thu hoạch
            "FROM tbl_plant tp " +
            "JOIN tbl_type_plant ttp ON tp.type_plant_id = ttp.id " +
            "WHERE tp.farm_id = :farm_id AND tp.is_deleted = 0  " +
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

    @Query(value = "SELECT " + "SUM(tp.area) as area_planted, " +   // Tổng diện tích cây trồng
            "(SELECT SUM(area) FROM tbl_farm) AS area, " +  // Tổng diện tích của tất cả các farm
            "SUM(tp.yield) as yield_total, " +   // Tổng sản lượng của từng cây trồng
            "SUM(tp.price * tp.yield) as price_total " +    // Tổng giá trị tiền từ cây trồng
            "FROM tbl_plant tp " + "JOIN tbl_farm f ON tp.farm_id = f.id " + "WHERE tp.is_deleted = 0",
            nativeQuery = true)
    List<Object[]> getTotalPlantedAreaAllFarm();


    static void toAllFarmGeneralResponse(AllFarmGeneralResponse allFarmGeneralResponse, Object[] objects) {
        allFarmGeneralResponse.setAreaPlanted((Double) objects[0]);
        allFarmGeneralResponse.setArea((Double) objects[1]);
        allFarmGeneralResponse.setYieldTotal((Double) objects[2]);
        allFarmGeneralResponse.setPriceTotal((Double) objects[3]);
    }

    @Query(value = """
            WITH months AS (
                SELECT generate_series(1, 12) AS month
            )
            SELECT 
                m.month,
                COALESCE(p.type_plant_id, h.type_plant_id) AS type_plant_id,
                tp.name AS type_plant_name,
                SUM(p.total_yield) AS total_yield_planned,
                SUM(p.total_money) AS total_money_planned,
                SUM(h.total_yield) AS total_yield_actual,
                SUM(h.total_money) AS total_money_actual
            FROM 
                months m
            LEFT JOIN (
                SELECT 
                    EXTRACT(MONTH FROM p.date_fruiting_stage_finish) AS month,
                    p.type_plant_id,
                    SUM(p.yield) AS total_yield,
                    SUM(p.yield * p.price) AS total_money
                FROM 
                    tbl_plant p
                WHERE 
                    p.farm_id IS NOT NULL AND
                    p.is_deleted = 0
                GROUP BY 
                    EXTRACT(MONTH FROM p.date_fruiting_stage_finish),
                    p.type_plant_id
            ) p ON m.month = p.month
            LEFT JOIN (
                SELECT 
                    EXTRACT(MONTH FROM h.create_at) AS month,
                    h.type_plant_id,
                    SUM(h.yield_actual) AS total_yield,
                    SUM(h.yield_actual * h.yield_actual) AS total_money
                FROM 
                    tbl_harvest h
                WHERE
                    h.is_deleted = 0
                GROUP BY 
                    EXTRACT(MONTH FROM h.create_at),
                    h.type_plant_id
            ) h ON m.month = h.month AND p.type_plant_id = h.type_plant_id
            LEFT JOIN tbl_type_plant tp ON COALESCE(p.type_plant_id, h.type_plant_id) = tp.id
            GROUP BY 
                m.month,
                COALESCE(p.type_plant_id, h.type_plant_id),
                tp.name
            ORDER BY 
                m.month;
            """, nativeQuery = true)
    List<Object[]> getMonthlyPlantAndHarvestSummary();

    static void toMonthlyPlantHarvestSummaryDTO(MonthlyPlantHarvestSummaryDTO monthlyPlantHarvestSummaryDTO,
                                                Object[] objects) {
        monthlyPlantHarvestSummaryDTO.setMonth((Integer) objects[0]);
        monthlyPlantHarvestSummaryDTO.setTypePlantId((String) objects[1]);
        monthlyPlantHarvestSummaryDTO.setTypePlantName((String) objects[2]);
        monthlyPlantHarvestSummaryDTO.setTotalYieldPlanned((Double) objects[3]);
        monthlyPlantHarvestSummaryDTO.setTotalMoneyPlanned((Double) objects[4]);
        monthlyPlantHarvestSummaryDTO.setTotalYieldActual((Double) objects[5]);
        monthlyPlantHarvestSummaryDTO.setTotalMoneyActual((Double) objects[6]);
    }


    @Query(value = "SELECT " +
            "tp.id AS plant_id, " +
            "tp.name AS plant_name, " +
            "tp.farm_id AS farm_id, " +
            "tf.name AS farm_name, " +
            "ttp.id AS type_plant_id, " +
            "ttp.name AS type_plant_name, " +
            "tp.yield AS total_yield_planted, " +
            "tp.price AS total_money_planted, " +
            "th.yield_actual AS total_yield_actual, " +
            "th.price_actual AS total_money_actual " +
            "FROM tbl_harvest th " +
            "LEFT JOIN tbl_plant tp ON tp.id = th.plant_id " +
            "LEFT JOIN tbl_farm tf ON tf.id = tp.farm_id " +
            "LEFT JOIN tbl_type_plant ttp ON ttp.id = tp.type_plant_id " +
            "WHERE EXTRACT(MONTH FROM th.create_at) = :month " +
            "AND EXTRACT(YEAR FROM th.create_at) = :year ",
            nativeQuery = true)
    List<Object[]> getReportDashboard(@Param("month") int month, @Param("year") int year);

    static void toHarvestReport(HarvestReport harvestReport, Object[] objects) {
        harvestReport.setPlantId(objects[0] != null ? (String) objects[0] : "");
        harvestReport.setPlantName(objects[1] != null ? (String) objects[1] : "");
        harvestReport.setFarmId(objects[2] != null ? (String) objects[2] : "");
        harvestReport.setFarmName(objects[3] != null ? (String) objects[3] : "");
        harvestReport.setTypePlantId(objects[4] != null ? (String) objects[4] : "");
        harvestReport.setTypePlantName(objects[5] != null ? (String) objects[5] : "");
        harvestReport.setTotalYieldPlanted(objects[6] != null ? (Double) objects[6] : 0.0);
        harvestReport.setTotalMoneyPlanted(objects[7] != null ? (Double) objects[7] : 0.0);
        harvestReport.setTotalYieldActual(objects[8] != null ? (Double) objects[8] : 0.0);
        harvestReport.setTotalMoneyActual(objects[9] != null ? (Double) objects[9] : 0.0);
    }


    // Reference How to query with key word ? in Spring JPA
    // @Query(value = "SELECT * FROM employee e WHERE e.department = ?1 AND e.salary > ?2", nativeQuery = true)
    // List<Employee> findEmployeesByDepartmentAndSalary(String department, Double salary);


}
