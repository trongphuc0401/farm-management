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
 * TypePlantEntity -
 *
 * @param
 * @return
 * @throws
 */

@Entity
@Table(name = "tbl_type_plant")
@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TypePlantEntity extends BaseEntity  {

    @Column
    String code;

    @Column(nullable = false)
    String name;

    @Column()
    String description;
}
