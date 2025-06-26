package org.example.xiaomibms.service;

import org.example.xiaomibms.entity.BatterySignal;
import org.springframework.beans.factory.annotation.Autowired;

public interface AlarmService {
    void processSignal(BatterySignal signal);
}
