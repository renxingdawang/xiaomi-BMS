package org.example.xiaomibms.controller;

import org.example.xiaomibms.dto.SignalReportDTO;
import org.example.xiaomibms.service.SignalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/signal")
public class SignalController {
    @Autowired
    private SignalService signalService;

    @PostMapping("/report")
    public String reportBatterySignal(@RequestBody List<SignalReportDTO> signalList) {
        signalService.saveSignals(signalList);
        return "ok";
    }
}
