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

    @Column(nullable = false)
    String typePlantId; // ID loại cây trồng

    @Column
    String farmId; // ID nông trại

    @Column(nullable = false)
    String name; // Tên cây trồng

    @Column
    String description; // Mô tả cây trồng

    @Column(nullable = false)
    Double area; // Diện tích cây trồng

    @Column(nullable = false)
    Double yield; // Sản lượng dự kiến

    @Column(nullable = false)
    Double price; // Giá cả

    @Column(nullable = false)
    Integer seedlingDay; // Số ngày để hoàn thành giai đoạn nảy mầm

    @Column(nullable = false)
    Integer vegetativeStageDay; //Số ngày để hoàn thành giai đoạn sinh trưởng

    @Column(nullable = false)
    Integer floweringStageDay; // Số ngày để hoàn thành giai đoạn ra hoa

    @Column(nullable = false)
    Integer fruitingStageDay; // Số ngày để hoàn thành giai đoạn tạo quả

    @Column
    LocalDateTime datePlanted; // Ngày bắt đầu trồng

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