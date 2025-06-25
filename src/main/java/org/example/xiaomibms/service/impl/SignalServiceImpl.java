package org.example.xiaomibms.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.xiaomibms.dto.SignalReportDTO;
import org.example.xiaomibms.entity.BatterySignal;
import org.example.xiaomibms.entity.VehicleInfo;
import org.example.xiaomibms.mapper.BatterySignalMapper;
import org.example.xiaomibms.mapper.VehicleInfoMapper;
import org.example.xiaomibms.service.SignalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class SignalServiceImpl implements SignalService {
    @Autowired
    private  BatterySignalMapper batterySignalMapper;
    @Autowired
    private  StringRedisTemplate redisTemplate;
    @Autowired
    private  ObjectMapper objectMapper;
    @Autowired
    private  VehicleInfoMapper vehicleInfoMapper;
    @Override
    public void saveSignals(List<SignalReportDTO> signalList){
        for(SignalReportDTO dto:signalList){
            try{
                JsonNode signalNode=objectMapper.readTree(dto.getSignal());
                BatterySignal signal=new BatterySignal();

                VehicleInfo vehicleInfo=vehicleInfoMapper.selectByCid(dto.getCid());
                signal.setVid(vehicleInfo.getVid());
                System.out.println(vehicleInfo);
                //System.out.println(vehicleInfo.getBatteryType());
                signal.setCid(dto.getCid());
                signal.setBatteryType(vehicleInfo.getBatteryType());
                signal.setSignalTime(LocalDateTime.now());

                if (signalNode.has("Mx")) signal.setMx(new BigDecimal(signalNode.get("Mx").asText()));
                if (signalNode.has("Mi")) signal.setMi(new BigDecimal(signalNode.get("Mi").asText()));
                if (signalNode.has("Ix")) signal.setIx(new BigDecimal(signalNode.get("Ix").asText()));
                if (signalNode.has("Ii")) signal.setIi(new BigDecimal(signalNode.get("Ii").asText()));

                batterySignalMapper.insert(signal);

                String redisKey="battery_signal:"+signal.getVid();
                redisTemplate.opsForValue().set(redisKey, dto.getSignal());
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
