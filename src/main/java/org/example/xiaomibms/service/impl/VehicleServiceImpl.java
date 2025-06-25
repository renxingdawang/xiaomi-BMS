package org.example.xiaomibms.service.impl;

import org.example.xiaomibms.dto.VehicleAddDTO;
import org.example.xiaomibms.entity.VehicleInfo;
import org.example.xiaomibms.mapper.VehicleInfoMapper;
import org.example.xiaomibms.service.VehicleService;
import org.example.xiaomibms.utils.Snowflake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class VehicleServiceImpl implements VehicleService {
    @Autowired
    private VehicleInfoMapper vehicleInfoMapper;
    @Override
    public String addVehicle(VehicleAddDTO dto){
        String vid = Snowflake.SnowflakeUid();
        VehicleInfo vehicleInfo=new VehicleInfo();
        vehicleInfo.setVid(vid);
        vehicleInfo.setCid(dto.getCid());
        vehicleInfo.setBatteryType(dto.getBatteryType());
        vehicleInfo.setTotalMileage(dto.getTotalMileage());
        vehicleInfo.setBatteryHealthStatus(dto.getBatteryHealthStatus());
        vehicleInfo.setCreateTime(LocalDateTime.now());
        vehicleInfo.setUpdateTime(LocalDateTime.now());

        vehicleInfoMapper.insert(vehicleInfo);

        return vid;
    }
}
