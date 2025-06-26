package org.example.xiaomibms.consumer;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.example.xiaomibms.entity.BatterySignal;
import org.example.xiaomibms.service.AlarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RocketMQMessageListener(topic="alarm-topic",consumerGroup = "bms-consumer-group")
public class BatterySignalConsumer implements RocketMQListener<BatterySignal> {
    private final AlarmService alarmService;

    public BatterySignalConsumer(AlarmService alarmService) {
        this.alarmService = alarmService;
    }

    @Override
    public void onMessage(BatterySignal signal){
        alarmService.processSignal(signal);
    }

}
