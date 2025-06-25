package org.example.xiaomibms.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.example.xiaomibms.dto.SignalReportDTO;
import org.example.xiaomibms.entity.BatterySignal;
import org.example.xiaomibms.mapper.BatterySignalMapper;
import org.example.xiaomibms.service.SignalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SignalServiceImpl implements SignalService {
    @Autowired
    private  BatterySignalMapper batterySignalMapper;
    private  StringRedisTemplate redisTemplate;
    private  ObjectMapper objectMapper;

    @Override
    public void saveSignals(List<SignalReportDTO> signalList){

    }
}
