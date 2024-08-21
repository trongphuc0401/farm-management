package vn.edu.likelion.farm_management.service.plant;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.edu.likelion.farm_management.common.exceptions.AppException;
import vn.edu.likelion.farm_management.common.exceptions.ErrorCode;
import vn.edu.likelion.farm_management.dto.request.plant.PlantCreationRequest;
import vn.edu.likelion.farm_management.dto.request.plant.PlantUpdateInfoRequest;
import vn.edu.likelion.farm_management.dto.response.plant.PaginatePlantResponse;
import vn.edu.likelion.farm_management.dto.response.plant.PlantResponse;
import vn.edu.likelion.farm_management.dto.response.plant.TypePlantResponse;
import vn.edu.likelion.farm_management.entity.PlantEntity;
import vn.edu.likelion.farm_management.mapper.PlantMapper;
import vn.edu.likelion.farm_management.repository.PlantRepository;
import vn.edu.likelion.farm_management.repository.TypePlantRepository;

import java.util.ArrayList;
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

    @Autowired
    TypePlantRepository typePlantRepository;


        @Override
        public Optional<PlantResponse> create(PlantCreationRequest plantCreationRequest) {
                PlantEntity plantEntity = plantMapper.toCreatePlant(plantCreationRequest);
                plantEntity = plantRepository.save(plantEntity);
                PlantResponse plantResponse = plantMapper.toPlantResponse(plantEntity);
                return Optional.of(plantResponse);

        }

    @Override
    public Optional<PlantResponse> update(PlantCreationRequest t)
    {
        return Optional.empty();
    }

    @Override
    public List<PlantResponse> saveAll(List<PlantEntity> ts) {
        return List.of();
    }

    @Override
    public void delete(String id) {
            PlantEntity plantEntity = plantRepository.findById(id).
                    orElseThrow(()-> new AppException(ErrorCode.PLANT_NOT_EXIST));

            plantEntity.setIsDeleted(1);
            plantRepository.delete(plantEntity);

    }

    @Override
    public void deleteAll(List<String> listId) {

    }

    @Override
    public Optional<PlantResponse> findById(String id) {
        return plantRepository.findById(id)
                .map(plantMapper::toPlantResponse)
                .or(() -> {
                    throw  new AppException(ErrorCode.PLANT_NOT_EXIST);
                });
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

    @Override
    public PaginatePlantResponse getAllByPagination(int pageNo, int pagSize) {
        Pageable pageable = PageRequest.of(pageNo,pagSize);
        Page<PlantEntity> plantEntities = plantRepository.findAll(pageable);
        if (plantEntities.isEmpty()) {
            throw new AppException(ErrorCode.PLANT_NOT_EXIST);
        }
        List<PlantEntity> plantEntityList = plantEntities.getContent();
        List<PlantResponse> data = plantEntityList.stream().map(plantMapper::toPlantResponse).toList();
        PaginatePlantResponse paginatePlantResponse = new PaginatePlantResponse();
        paginatePlantResponse.setResults(data);
        paginatePlantResponse.setPageNo(plantEntities.getNumber());
        paginatePlantResponse.setPageSize(plantEntities.getSize());
        paginatePlantResponse.setTotalElements(plantEntities.getNumberOfElements());
        paginatePlantResponse.setTotalPages(plantEntities.getTotalPages());
        return paginatePlantResponse;
    }

    @Override
    public List<TypePlantResponse> findAllTypePlant() {
        var typePlantEntities = typePlantRepository.findAll();

        if (typePlantEntities.isEmpty()) {
            throw new AppException(ErrorCode.TYPE_PLANT_NOT_EXIST);
        }
        return  typePlantEntities.stream()
                .map(plantMapper::toTypePlantResponse)
                .toList();
    }

    @Override
    public Optional<PlantResponse> updateInfo(String id, PlantUpdateInfoRequest plantUpdateInfoRequest) {
       PlantEntity plantEntity = plantRepository.findById(id)
               .orElseThrow(() -> new AppException(ErrorCode.PLANT_NOT_EXIST));

       plantMapper.updatePlantEntity(plantEntity, plantUpdateInfoRequest);

       PlantEntity updatedPlantEntity = plantRepository.save(plantEntity);
       PlantResponse plantResponse = plantMapper.toPlantResponse(updatedPlantEntity);
     return Optional.of(plantResponse);
    }


}
