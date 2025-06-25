package org.example.xiaomibms.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class BatterySignal {
    private String vid;
    private Integer cid;

    private String batteryType;
    private BigDecimal mx;

    private BigDecimal mi;

    private BigDecimal ix;

    private BigDecimal ii;

    private LocalDateTime signalTime;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
