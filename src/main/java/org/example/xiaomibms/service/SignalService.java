package org.example.xiaomibms.service;

import org.example.xiaomibms.dto.SignalReportDTO;

import java.util.List;

public interface SignalService {
    void saveSignals(List<SignalReportDTO> signalList);
    String querySignal(Integer cid);
    void updateSignal(Integer cid, String newSignalJson);
    void deleteSignal(Integer cid);
}
