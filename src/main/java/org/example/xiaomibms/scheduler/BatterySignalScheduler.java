package org.example.xiaomibms.scheduler;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.example.xiaomibms.entity.BatterySignal;
import org.example.xiaomibms.mapper.BatterySignalMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

import static org.reflections.Reflections.log;

@Component
public class BatterySignalScheduler {
    private final BatterySignalMapper batterySignalMapper;
    private final RocketMQTemplate rocketMQTemplate;

    public BatterySignalScheduler(BatterySignalMapper batterySignalMapper, RocketMQTemplate rocketMQTemplate) {
        this.batterySignalMapper = batterySignalMapper;
        this.rocketMQTemplate = rocketMQTemplate;
    }

    @Scheduled(fixedRate = 30000)
    public void scanAndSend(){
        System.out.println("定时任务执行中：{}");
        List<BatterySignal>signals=batterySignalMapper.findRecentSignals();
        for(BatterySignal signal:signals){
            rocketMQTemplate.syncSend("alarm-topic",signal);
            System.out.println("Send message");
        }
    }


}
