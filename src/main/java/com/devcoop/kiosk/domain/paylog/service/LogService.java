package com.devcoop.kiosk.domain.paylog.service;

import com.devcoop.kiosk.domain.paylog.presentation.dto.PayLogRequestDto;
import org.springframework.http.ResponseEntity;

public interface LogService {
    ResponseEntity<Object> savePayLog(PayLogRequestDto payLogRequestDto);
}