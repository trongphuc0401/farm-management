package vn.edu.likelion.farm_management.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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

    @Column
    String plantId; // ID của cây trồng

    @Column
    String plantName; // Tên cây trồng

    @Column
    String typePlantId; // ID loại cây trồng

    @Column
    String farmId; // ID nông trại

    @Column
    String farmName; // Tên nông trại

    @Column
    String description; // Mô tả cây trồng

    @Column
    Double yieldCurrently; // Sản lượng hiện tại

    @Column
    Double priceCurrently; // Giá hiện tại


}
