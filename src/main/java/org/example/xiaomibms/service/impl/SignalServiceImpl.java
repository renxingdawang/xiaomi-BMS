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
    @Override
    public String querySignal(Integer cid){
        VehicleInfo vehicleInfo=vehicleInfoMapper.selectByCid(cid);
        String redisKey = "battery_signal:" + vehicleInfo.getVid();
        // 1. 先从 Redis 查询
        String cached = redisTemplate.opsForValue().get(redisKey);
        if (cached != null) {
            return "data from redis: " + cached;
        }
        // 2. 如果缓存没有，则查询数据库
        BatterySignal latest = batterySignalMapper.selectLatestByVid(cid);
        if (latest == null) {
            return "no exist data";
        }
        try {
            String json = objectMapper.writeValueAsString(latest);
            redisTemplate.opsForValue().set(redisKey, json); // 写入缓存
            return "data from MySQL: " + json;
        } catch (Exception e) {
            throw new RuntimeException("Error Json", e);
        }
    }

    @Override
    public void updateSignal(Integer cid, String newSignalJson) {
        VehicleInfo vehicleInfo=vehicleInfoMapper.selectByCid(cid);
        String vid=vehicleInfo.getVid();

        try{
            JsonNode signalNode=objectMapper.readTree(newSignalJson);
            BatterySignal signal=new BatterySignal();
            signal.setVid(vid);
            signal.setCid(cid);
            signal.setBatteryType(vehicleInfo.getBatteryType());
            signal.setSignalTime(LocalDateTime.now());

            if (signalNode.has("Mx")) signal.setMx(new BigDecimal(signalNode.get("Mx").asText()));
            if (signalNode.has("Mi")) signal.setMi(new BigDecimal(signalNode.get("Mi").asText()));
            if (signalNode.has("Ix")) signal.setIx(new BigDecimal(signalNode.get("Ix").asText()));
            if (signalNode.has("Ii")) signal.setIi(new BigDecimal(signalNode.get("Ii").asText()));

            int updated=batterySignalMapper.updateByCid(cid,signal);
            if(updated>0){
                String redisKey="battery_signal:"+vid;
                redisTemplate.opsForValue().set(redisKey,newSignalJson);
            }
        }  catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteSignal(Integer cid) {
        VehicleInfo vehicleInfo=vehicleInfoMapper.selectByCid(cid);
        String vid=vehicleInfo.getVid();

        int deleted=batterySignalMapper.deleteByCid(cid);
        if(deleted>0){
            String redisKey="battery_signal:"+vid;
            redisTemplate.delete(redisKey);
        }
    }
}
