package vn.edu.likelion.farm_management.dto.response.plant;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

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
public class PaginatePlantResponse {
    List<PlantResponse> data;
    int pageNo;
    int  pageSize;
    int totalElements;
    int totalPages;
}
