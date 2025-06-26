package org.example.xiaomibms.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.xiaomibms.entity.BatterySignal;

import java.util.List;
@Mapper
public interface BatterySignalMapper {
    int insert(BatterySignal batterySignal);
    List<BatterySignal> selectByVid(String vid);
    BatterySignal selectLatestByVid(Integer cid);
    List<BatterySignal> findRecentSignals();

    int updateByCid(Integer cid, BatterySignal signal);
    int deleteByCid(Integer cid);
}
