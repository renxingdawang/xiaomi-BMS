package org.example.xiaomibms.entity;

import lombok.Data;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@Setter
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
