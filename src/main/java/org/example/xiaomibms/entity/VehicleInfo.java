package org.example.xiaomibms.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "vehicle_info")
public class VehicleInfo {

    @Id
    private String vid;
    private Integer cid;

    @Column(name = "battery_type")
    private String batteryType;

    @Column(name = "total_mileage")
    private Long totalMileage;

    @Column(name = "battery_health_status")
    private Integer batteryHealthStatus;

    @Column(name = "create_time")
    private LocalDateTime createTime;
    @Column(name = "update_time")
    private LocalDateTime updateTime;

    @Column(name = "delete_time")
    private LocalDateTime deleteTime;
}
