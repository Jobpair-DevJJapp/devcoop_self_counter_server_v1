package com.devcoop.kiosk.domain.service;

import com.devcoop.kiosk.domain.entity.PayLogEntity;
import com.devcoop.kiosk.domain.entity.UserEntity;
import com.devcoop.kiosk.domain.presentation.dto.PayLogRequestDto;
import com.devcoop.kiosk.domain.repository.PayLogRepository;
import com.devcoop.kiosk.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class LogServiceImpl implements LogService {

    private final PayLogRepository payLogRepository;

    private final UserRepository userRepository;

    private final PayLogEntity payLogEntity;

    @Autowired
    public LogServiceImpl(PayLogRepository payLogRepository, UserRepository userRepository, PayLogEntity payLogEntity) {
        this.payLogRepository = payLogRepository;
        this.userRepository = userRepository;
        this.payLogEntity = payLogEntity;
    }

    @Override
    public void savePayLog(PayLogRequestDto payLogRequestDto) {
        payLogEntity.setCodeNumber(payLogRequestDto.getCodeNumber());
        payLogEntity.setDate(LocalDate.now());
        payLogEntity.setInnerPoint(payLogRequestDto.getInnerPoint());

        UserEntity user = userRepository.findPointByCodeNumber(payLogRequestDto.getCodeNumber());
        payLogEntity.setPoint(user.getPoint() - payLogRequestDto.getInnerPoint());

        payLogEntity.setChargerId(payLogRequestDto.getChargerId());
        payLogEntity.setVerifyKey("test");
        payLogEntity.setStudentName(payLogRequestDto.getStudentName());

        payLogRepository.save(payLogEntity);
    }
}