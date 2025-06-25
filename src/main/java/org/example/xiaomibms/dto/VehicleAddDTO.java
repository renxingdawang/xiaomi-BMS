package org.example.xiaomibms.dto;

import lombok.Data;

@Data
public class VehicleAddDTO {
    private Integer cid;
    private String batteryType;
    private Long totalMileage;
    private Integer batteryHealthStatus;
}
