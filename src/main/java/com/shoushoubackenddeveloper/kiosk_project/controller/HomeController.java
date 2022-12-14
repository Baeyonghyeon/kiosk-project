package com.shoushoubackenddeveloper.kiosk_project.controller;

import com.shoushoubackenddeveloper.kiosk_project.dto.response.SingleResponseDto;
import com.shoushoubackenddeveloper.kiosk_project.service.OrderService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/")
public class HomeController {

    private final OrderService orderService;

    @GetMapping
    public ResponseEntity helloKiosk(HttpServletResponse response) {
        String orderId = orderService.createOrder().getId().toString();
        Cookie cookie = new Cookie("order_id", orderId);


        response.addCookie(cookie);

        return new ResponseEntity<>(
                new SingleResponseDto<>(null),
                HttpStatus.OK);
    }

}
