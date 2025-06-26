package org.example.xiaomibms.controller;

import org.example.xiaomibms.dto.SignalReportDTO;
import org.example.xiaomibms.response.ApiResponse;
import org.example.xiaomibms.service.SignalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/signal")
public class SignalController {
    private final SignalService signalService;

    public SignalController(SignalService signalService) {
        this.signalService = signalService;
    }

    @PostMapping("/report")
    public ApiResponse<String> reportBatterySignal(@RequestBody List<SignalReportDTO> signalList) {
        signalService.saveSignals(signalList);
        return new ApiResponse<>(200, "ok", "Update ok");
    }

    @GetMapping("/query")
    public ApiResponse<String> querySignal(@RequestParam Integer cid) {
        String result=signalService.querySignal(cid);
        return new ApiResponse<>(200, "ok", result);
    }
    @PutMapping("/update")
    public ApiResponse<Integer> update(@RequestParam Integer cid,@RequestBody String signalJson){
        signalService.updateSignal(cid,signalJson);
        return new ApiResponse<>(200, "ok", cid);
    }
    @DeleteMapping("/delete")
    public ApiResponse<Integer> delete(@RequestParam Integer cid){
        signalService.deleteSignal(cid);
        return new ApiResponse<>(200, "ok", cid);
    }

}
