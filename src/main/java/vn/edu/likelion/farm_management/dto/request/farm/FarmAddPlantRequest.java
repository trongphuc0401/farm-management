package vn.edu.likelion.farm_management.dto.request.farm;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

/**
 * FarmAddPlantRequest -
 *
 * @param
 * @return
 * @throws
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FarmAddPlantRequest {

    @NotNull(message = "PARAM_NOT_NULL")
    String farmId;

    @NotNull
    List<String> plantIdList;

}
