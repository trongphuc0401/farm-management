package vn.edu.likelion.farm_management.dto.response.harvest;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import vn.edu.likelion.farm_management.dto.response.plant.PlantResponse;

import java.util.List;

/**
 * PaginatePlantResponse -
 *
 * @param
 * @return
 * @throws
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HarvestResponsePaginate {
    List<HarvestResponse> results;
    int pageNo;
    int  pageSize;
    int totalElements;
    int totalPages;
}
