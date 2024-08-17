package vn.edu.likelion.farm_management.service.plant;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.likelion.farm_management.common.exceptions.AppException;
import vn.edu.likelion.farm_management.common.exceptions.ErrorCode;
import vn.edu.likelion.farm_management.dto.request.plant.PlantCreationRequest;
import vn.edu.likelion.farm_management.dto.response.plant.PlantResponse;
import vn.edu.likelion.farm_management.entity.PlantEntity;
import vn.edu.likelion.farm_management.mapper.PlantMapper;
import vn.edu.likelion.farm_management.repository.PlantRepository;

import java.util.List;
import java.util.Optional;

/**
 * PlantServiceImpl -
 *
 * @param
 * @return
 * @throws
 */
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PlantServiceImpl  implements PlantService{

    @Autowired
    PlantRepository plantRepository;

    @Autowired
    PlantMapper plantMapper;


        @Override
        public Optional<PlantResponse> create(PlantCreationRequest plantCreationRequest) {
                PlantEntity plantEntity = plantMapper.toPlant(plantCreationRequest);
                plantEntity = plantRepository.save(plantEntity);
                PlantResponse plantResponse = plantMapper.toPlantResponse(plantEntity);
                return Optional.of(plantResponse);

        }

    @Override
    public Optional<PlantResponse> update(PlantCreationRequest t) {
        return Optional.empty();
    }

    @Override
    public List<PlantResponse> saveAll(List<PlantEntity> ts) {
        return List.of();
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public void deleteAll(List<String> listId) {

    }

    @Override
    public Optional<PlantResponse> findById(String id) {
        return Optional.empty();
    }

    @Override
    public List<PlantResponse> findAll() {
        var plantEntities = plantRepository.findAll();

        if (plantEntities.isEmpty()) {
            throw new AppException(ErrorCode.PLANT_NOT_EXIST);
        }
        return  plantEntities.stream()
                .map(plantMapper::toPlantResponse)
                .toList();
    }
}
