package org.example.xiaomibms.controller;

import org.example.xiaomibms.dto.VehicleAddDTO;
import org.example.xiaomibms.response.ApiResponse;
import org.example.xiaomibms.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vehicle")
public class VehicleController {
    private final VehicleService  vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @PostMapping("/add")
    public ApiResponse<String> addVehicle(@RequestBody VehicleAddDTO dto){
        String vid=vehicleService.addVehicle(dto);
        return new ApiResponse<>(200, "ok", "Add ok,vid: "+vid);
    }
}
