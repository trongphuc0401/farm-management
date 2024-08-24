package vn.edu.likelion.farm_management.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import vn.edu.likelion.farm_management.common.enums.StatusFarm;

/**
 * FarmEntity -
 *
 * @param
 * @return
 * @throws
 */
@Entity
@Table(name = "tbl_farm")
@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FarmEntity extends BaseEntity{
    @Column(nullable = false)
    String name;

    @Enumerated(EnumType.STRING)
    @Column
    StatusFarm status;

    @Column(nullable = false)
    Double area;

    @Column
    String description;

}
