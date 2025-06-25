package org.example.xiaomibms.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "alarm_rule", indexes = {
        @Index(name = "idx_battery_diff", columnList = "battery_type, diff_type"),
        @Index(name = "idx_diff_range", columnList = "min_diff, max_diff")
})
public class AlarmRule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "rule_id")
    private Integer ruleId;

    @Column(name = "alarm_type")
    private String alarmType;

    @Column(name = "battery_type")
    private String batteryType;

    @Column(name = "diff_type")
    private String diffType;

    @Column(name = "min_diff")
    private BigDecimal minDiff;

    @Column(name = "max_diff")
    private BigDecimal maxDiff;

    @Column(name = "alarm_level")
    private Integer alarmLevel;

    @Column(name = "create_time")
    private LocalDateTime createTime;

    @Column(name = "delete_time")
    private LocalDateTime deleteTime;
}
