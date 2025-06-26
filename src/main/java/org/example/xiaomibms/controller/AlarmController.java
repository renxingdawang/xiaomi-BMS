package org.example.xiaomibms.controller;

import org.example.xiaomibms.entity.AlarmInfo;
import org.example.xiaomibms.mapper.AlarmInfoMapper;
import org.example.xiaomibms.service.AlarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alarm")
public class AlarmController {
    @Autowired
    private AlarmInfoMapper alarmInfoMapper;

    @GetMapping("/vehicle")
    public List<AlarmInfo>getAlarms(@RequestParam Integer cid){
        return alarmInfoMapper.selectByCid(cid);
    }
}
