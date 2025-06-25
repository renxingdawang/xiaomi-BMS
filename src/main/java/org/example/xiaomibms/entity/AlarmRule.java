package org.example.xiaomibms.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class AlarmRule {
    private Integer id;

    private Integer ruleId;

    private String alarmType;

    private String batteryType;

    private String diffType;

    private BigDecimal minDiff;

    private BigDecimal maxDiff;

    private Integer alarmLevel;

    private LocalDateTime createTime;

    private LocalDateTime deleteTime;
}
