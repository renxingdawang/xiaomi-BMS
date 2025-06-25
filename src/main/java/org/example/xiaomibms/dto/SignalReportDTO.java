package org.example.xiaomibms.dto;

import lombok.Data;

@Data
public class SignalReportDTO {
    private Integer cid;
    private Integer warnId; // 可选
    private String signal;
}
