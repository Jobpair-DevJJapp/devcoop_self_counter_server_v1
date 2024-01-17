package com.devcoop.kiosk.domain.user.service;

import com.devcoop.kiosk.domain.user.User;
import com.devcoop.kiosk.domain.user.presentation.dto.UserPointRequestDto;
import com.devcoop.kiosk.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class UserPointServiceImpl implements UserPointService {

    private final UserRepository userRepository;
    public Object deductPoints(UserPointRequestDto userPointRequestDto) {
        System.out.println("deductPoint 실행");
        User user = userRepository.findByCodeNumber(userPointRequestDto.getCodeNumber());

        System.out.println(user);

        try {
            if (user != null && user.getPoint() >= userPointRequestDto.getTotalPrice()) {
                System.out.println("if 문 통과");
                int newPoint = user.getPoint() - userPointRequestDto.getTotalPrice();
                System.out.println(newPoint);
                user.setPoint(newPoint);
                userRepository.save(user);

                System.out.println("결제 후 남은 포인트는 : " + newPoint + "원 입니다.");
                return newPoint;
            }

            return ResponseEntity.ok().build();
        } catch(Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
