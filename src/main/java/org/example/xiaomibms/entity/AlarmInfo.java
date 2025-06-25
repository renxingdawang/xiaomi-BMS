package org.example.xiaomibms.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "alarm_info", indexes = {
        @Index(name = "idx_vid_time", columnList = "vid, signal_time"),
        @Index(name = "idx_rule_id", columnList = "rule_id")
})
public class AlarmInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String vid;

    private Integer cid;

    @Column(name = "battery_type")
    private String batteryType;

    @Column(name = "rule_id")
    private Integer ruleId;

    @Column(name = "rule_name")
    private String ruleName;

    @Column(name = "alarm_level")
    private Integer alarmLevel;

    @Column(name = "is_alarm")
    private Boolean isAlarm;

    @Column(name = "signal_data", columnDefinition = "json")
    private String signalData;

    @Column(name = "signal_time")
    private LocalDateTime signalTime;

    @Column(name = "create_time")
    private LocalDateTime createTime;
}
