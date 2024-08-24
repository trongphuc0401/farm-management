package vn.edu.likelion.farm_management.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

/**
 * HarvestEntity -
 *
 * @param
 * @return
 * @throws
 */
@Entity
@Table(name = "tbl_harvest")
@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HarvestEntity extends BaseEntity {

//    @OneToOne
//    @JoinColumn(name = "plant_id", nullable = false, unique = true)
//    PlantEntity plant; // Liên kết với cây trồng

//    @Column(nullable = false)
//    String plantId; // ID của cây trồng

    @Column(name = "plant_id", nullable = false)
    String plantId; // Khóa ngoại của cây trồng

    @OneToOne
    @JoinColumn(name = "plant_id", referencedColumnName = "id", insertable = false, updatable = false)
    PlantEntity plant; // Liên kết với cây trồng

    @Column(nullable = false)
    String plantName; // Tên cây trồng

    @Column(nullable = false)
    String typePlantId; // ID loại cây trồng

    @Column(nullable = false)
    String farmId; // ID nông trại

    @Column(nullable = false)
    String farmName; // Tên nông trại

    @Column
    String description; // Mô tả cây trồng

    @Column(nullable = false)
    Double yieldActual; // Sản lượng hiện tại có thể thu hoạch (kg)

    @Column(nullable = false)
    Double priceActual; // Giá hiện tại trên 1 (kg)

}
