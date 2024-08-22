package vn.edu.likelion.farm_management.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.likelion.farm_management.common.constants.ApiPath;
import vn.edu.likelion.farm_management.common.restfulAPI.ResponseUtil;
import vn.edu.likelion.farm_management.service.farm.FarmService;
import vn.edu.likelion.farm_management.service.plant.PlantService;

/**
 * FarmController -
 *
 * @param
 * @return
 * @throws
 */

@RestController
@RequestMapping(ApiPath.FARM_API)
@RequiredArgsConstructor
public class FarmController {

    @Autowired
    ResponseUtil responseUtil;

    @Autowired
    FarmService farmService;


}
