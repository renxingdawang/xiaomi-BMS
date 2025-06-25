package org.example.xiaomibms.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.xiaomibms.entity.BatterySignal;

import java.util.List;
@Mapper
public interface BatterySignalMapper {
    int insert(BatterySignal batterySignal);
    List<BatterySignal> selectByVid(String vid);
}
