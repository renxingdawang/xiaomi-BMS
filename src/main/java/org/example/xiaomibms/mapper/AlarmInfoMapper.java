package org.example.xiaomibms.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.xiaomibms.entity.AlarmInfo;
import org.example.xiaomibms.entity.VehicleInfo;

import java.util.List;
@Mapper
public interface AlarmInfoMapper {
    int insert(AlarmInfo alarmInfo);
    List<AlarmInfo> selectByVid(String vid);
}

