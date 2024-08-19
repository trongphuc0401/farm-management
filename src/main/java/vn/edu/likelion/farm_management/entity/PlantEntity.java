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
    String name;

    @Column
    String description;

    @Column
    LocalDateTime datePlanted;

    @Column
    Double sellingDate;

    @Column
    Double vegetativeStageDate;

    @Column
    Double floweringStageDate;

    @Column
    Double fruitingStageDate;

    @Column
    Double area;

    @Column
    Double expectedYield;

    @Column
    Double price;

    @Column
    String typePlantId;

    @Column
    int isDeleted;

    @Column
    String farmId;

}