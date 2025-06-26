package org.example.xiaomibms.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlarmInfoVO {
    @JsonProperty("车架编号")
    private Integer cid;

    @JsonProperty("电池类型")
    private String batteryType;

    @JsonProperty("warnName")
    private String warnName;

    @JsonProperty("warnLevel")
    private Integer warnLevel;
}
