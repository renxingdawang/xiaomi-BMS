package org.example.xiaomibms.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class VehicleInfo {
    private String vid;
    private Integer cid;
    private String batteryType;
    private Long totalMileage;
    private Integer batteryHealthStatus;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private LocalDateTime deleteTime;
}
