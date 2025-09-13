package com.ar7Enterprise.bank.core.service;

import com.ar7Enterprise.bank.core.model.LogEntry;
import jakarta.ejb.Remote;

public interface LogService {
    void saveLog(LogEntry log);
}
