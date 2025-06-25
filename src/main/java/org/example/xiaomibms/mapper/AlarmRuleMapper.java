package org.example.xiaomibms.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.xiaomibms.entity.AlarmRule;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface AlarmRuleMapper {
    List<AlarmRule> findRules(@Param("batteryType") String batteryType,
                              @Param("diffType") String diffType,
                              @Param("diffValue") BigDecimal diffValue);
}
