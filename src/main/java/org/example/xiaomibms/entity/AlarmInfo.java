package org.example.xiaomibms.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AlarmInfo {
    private Long id;

    private String vid;

    private Integer cid;

    private String batteryType;

    private Integer ruleId;

    private String ruleName;

    private Integer alarmLevel;

    private Boolean isAlarm;

    private String signalData;

    private LocalDateTime signalTime;

    private LocalDateTime createTime;
}
