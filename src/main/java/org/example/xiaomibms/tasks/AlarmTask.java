package org.example.xiaomibms.tasks;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.example.xiaomibms.dto.AlarmCheckDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Set;

@Slf4j
@Component
public class AlarmTask {
    @Autowired
    private StringRedisTemplate redisTemplate;
    //@Autowired
    private RocketMQTemplate rocketMQTemplate;
    @Scheduled(fixedRate = 300_000) // 每5分钟执行
    public void scanSignalsAndSendAlarm() {
        Set<String> keys = redisTemplate.keys("battery_signal:*");
        if (keys == null || keys.isEmpty()) return;

        for (String key : keys) {
            String signal = redisTemplate.opsForValue().get(key);
            if (signal == null) continue;

            String vid = key.replace("battery_signal:", "");
            AlarmCheckDTO req = new AlarmCheckDTO();
            req.setVid(vid);
            req.setSignal(signal);

            rocketMQTemplate.convertAndSend("alarm-topic", req);
            log.info("[AlarmTask] 已发送MQ预警消息: {}", req);
        }
    }
}
