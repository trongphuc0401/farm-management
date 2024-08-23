package vn.edu.likelion.farm_management.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.likelion.farm_management.common.constants.ApiPath;
import vn.edu.likelion.farm_management.common.restfulAPI.ResponseUtil;
import vn.edu.likelion.farm_management.common.restfulAPI.RestAPIResponse;
import vn.edu.likelion.farm_management.common.restfulAPI.RestAPIStatus;
import vn.edu.likelion.farm_management.service.farm.FarmService;
import vn.edu.likelion.farm_management.service.harvest.HarvestService;

/**
 * DashboardController -
 *
 * @param
 * @return
 * @throws
 */

@RestController
@RequestMapping(ApiPath.DASHBOARD_API)
@RequiredArgsConstructor
public class DashboardController {


    @Autowired
    ResponseUtil responseUtil;

    @Autowired
    HarvestService harvestService;

    @Autowired
    FarmService farmService;

    @GetMapping("/getMoneyDashboard")
    public ResponseEntity<RestAPIResponse<Object>> getMoneyAllFarmEachMonthsGroupByTypePlant() {
        return responseUtil.buildResponse(RestAPIStatus.OK, farmService.getMonthlyPlantAndHarvestSummary(),
                HttpStatus.OK);
    }




}
