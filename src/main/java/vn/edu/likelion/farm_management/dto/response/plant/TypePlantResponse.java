package vn.edu.likelion.farm_management.dto.response.plant;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

/**
 * TypePlantResponse -
 *
 * @param
 * @return
 * @throws
 */

@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TypePlantResponse {

    String id;

    String code;

    String name;

    int isDeleted;

    String description;

    // LocalDateTime createAt;
    //
    // LocalDateTime updateAt;
    

}
