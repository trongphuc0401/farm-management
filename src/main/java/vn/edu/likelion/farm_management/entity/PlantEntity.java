package vn.edu.likelion.farm_management.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

/**
 * PlantEntity - Object represebting a plan in the system
 *
 * <p>
 *     This class contain basic information of a user, including
 *     ID ,Name, Description, Date Planted, Selling Date, Vegetative Stage Date
 *     It is used to store and
 *   transfer plant information throughout the application
 * </p>
 * @author Nguyen Trong Phuc
 * @since 2024-08-16
 *
 */


@Entity
@Table(name = "tbl_plant")
@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PlantEntity extends BaseEntity {

    @Column
    String typePlantId; // ID loại cây trồng

    @Column
    String farmId; // ID nông trại

    @Column
    String name; // Tên cây trồng

    @Column
    String description; // Mô tả cây trồng

    @Column
    Double area; // Diện tích cây trồng

    @Column
    Double expectedYield; // Sản lượng dự kiến

    @Column
    Double price; // Giá cả

    @Column
    Integer seedlingDay; // Ngày bán

    @Column
    Integer vegetativeStageDay; // Ngày bắt đầu giai đoạn sinh trưởng

    @Column
    Integer floweringStageDay; // Ngày bắt đầu giai đoạn ra hoa

    @Column
    Integer fruitingStageDay; // Ngày bắt đầu giai đoạn tạo quả

    @Column
    LocalDateTime datePlanted; // Ngày trồng

    @Column
    LocalDateTime dateSeedlingFinish; // Ngày hoàn thành nảy mầm

    @Column
    LocalDateTime dateVegetativeStageFinish; // Ngày hoàn thành giai đoạn lá

    @Column
    LocalDateTime dateFloweringStageFinish; // Ngày hoàn thành giai đoạn ra hoa

    @Column
    LocalDateTime dateFruitingStageFinish; // Ngày hoàn thành giai đoạn tạo quả

    @Column
    LocalDateTime dateHarvestFinish; // Ngày hoàn thành thu hoạch


}