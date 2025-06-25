package org.example.xiaomibms.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "battery_signal", indexes = {
        @Index(name = "idx_vid_signal", columnList = "vid, signal_time")
})
public class BatterySignal {
    @Id
    private String vid;
    private Integer cid;

    @Column(name = "battery_type")
    private String batteryType;
    @Column(name = "Mx")
    private BigDecimal mx;
    @Column(name = "Mi")
    private BigDecimal mi;
    @Column(name = "Ix")
    private BigDecimal ix;
    @Column(name = "Ii")
    private BigDecimal ii;

    @Column(name = "signal_time")
    private LocalDateTime signalTime;

    @Column(name = "create_time")
    private LocalDateTime createTime;

    @Column(name = "update_time")
    private LocalDateTime updateTime;
}
