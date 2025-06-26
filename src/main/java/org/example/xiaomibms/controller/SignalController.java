package org.example.xiaomibms.controller;

import org.example.xiaomibms.dto.SignalReportDTO;
import org.example.xiaomibms.service.SignalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/query")
    public String querySignal(@RequestParam Integer cid) {
        return signalService.querySignal(cid);
    }
    @PutMapping("/update")
    public String update(@RequestParam Integer cid,@RequestBody String signalJson){
        signalService.updateSignal(cid,signalJson);
        return "ok";
    }
    @DeleteMapping("/delete")
    public String delete(@RequestParam Integer cid){
        signalService.deleteSignal(cid);
        return "ok";
    }

}
