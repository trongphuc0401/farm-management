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
    @Column
    String name;

    @Column
    String status;

    @Column
    Double area;

    @Column
    int isDeleted;
}
