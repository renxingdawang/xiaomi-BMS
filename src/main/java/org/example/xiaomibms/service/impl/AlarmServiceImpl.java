package org.example.xiaomibms.service.impl;

import com.alibaba.fastjson.JSON;
import org.example.xiaomibms.entity.AlarmInfo;
import org.example.xiaomibms.entity.AlarmRule;
import org.example.xiaomibms.entity.BatterySignal;
import org.example.xiaomibms.mapper.AlarmInfoMapper;
import org.example.xiaomibms.mapper.AlarmRuleMapper;
import org.example.xiaomibms.service.AlarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AlarmServiceImpl implements AlarmService {
    private final AlarmRuleMapper alarmRuleMapper;
    private final AlarmInfoMapper alarmInfoMapper;

    public AlarmServiceImpl(AlarmRuleMapper alarmRuleMapper, AlarmInfoMapper alarmInfoMapper) {
        this.alarmRuleMapper = alarmRuleMapper;
        this.alarmInfoMapper = alarmInfoMapper;
    }

    @Override
    public void processSignal(BatterySignal signal){
        Map<String, BigDecimal>diffs=new HashMap<>();
        if(signal.getMx()!=null&&signal.getMi()!=null){
            diffs.put("Mx-Mi",signal.getMx().subtract(signal.getMi()));
        }
        if(signal.getIx()!=null&&signal.getIi()!=null){
            diffs.put("Ix-Ii",signal.getIx().subtract(signal.getIi()));
        }

        for (Map.Entry<String,BigDecimal>entry: diffs.entrySet()){
            List<AlarmRule>rules=alarmRuleMapper.findRules(signal.getBatteryType(), entry.getKey(), entry.getValue());
            for (AlarmRule rule : rules) {
                AlarmInfo info = new AlarmInfo();
                info.setVid(signal.getVid());
                info.setCid(signal.getCid());
                info.setBatteryType(signal.getBatteryType());
                info.setRuleId(rule.getRuleId());
                info.setRuleName(rule.getAlarmType());
                info.setAlarmLevel(rule.getAlarmLevel());
                info.setIsAlarm(true);
                info.setSignalData(JSON.toJSONString(signal));
                info.setSignalTime(signal.getSignalTime());
                alarmInfoMapper.insert(info);
            }
        }
    }

}
