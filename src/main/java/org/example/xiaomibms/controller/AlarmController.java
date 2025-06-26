package org.example.xiaomibms.controller;

import org.example.xiaomibms.VO.AlarmInfoVO;
import org.example.xiaomibms.entity.AlarmInfo;
import org.example.xiaomibms.mapper.AlarmInfoMapper;
import org.example.xiaomibms.response.ApiResponse;
import org.example.xiaomibms.service.AlarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/alarm")
public class AlarmController {
    private final AlarmInfoMapper alarmInfoMapper;

    public AlarmController(AlarmInfoMapper alarmInfoMapper) {
        this.alarmInfoMapper = alarmInfoMapper;
    }

    @GetMapping("/vehicle")
    public ApiResponse<List<AlarmInfoVO>>getAlarms(@RequestParam Integer cid){
        List<AlarmInfo> alarms = alarmInfoMapper.selectByCid(cid);
        List<AlarmInfoVO> result=alarms.stream().map(alarmInfo -> {
            AlarmInfoVO vo = new AlarmInfoVO();
            vo.setCid(alarmInfo.getCid());
            vo.setBatteryType(alarmInfo.getBatteryType());
            vo.setWarnName(alarmInfo.getRuleName());
            vo.setWarnLevel(alarmInfo.getAlarmLevel());
            return vo;
        }).collect(Collectors.toList());
        return new ApiResponse<>(200, "ok", result);
    }
}
