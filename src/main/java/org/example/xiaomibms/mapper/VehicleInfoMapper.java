package org.example.xiaomibms.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.xiaomibms.entity.VehicleInfo;

import java.util.List;
@Mapper
public interface VehicleInfoMapper {
    void insert(VehicleInfo vehicleInfo);
    VehicleInfo selectByVid(String vid);
    List<VehicleInfo> selectAll();
    VehicleInfo selectByCid(Integer cid);
}
