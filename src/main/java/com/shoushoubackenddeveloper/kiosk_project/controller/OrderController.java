package com.shoushoubackenddeveloper.kiosk_project.controller;

import com.shoushoubackenddeveloper.kiosk_project.dto.CoffeeOrderDto;
import com.shoushoubackenddeveloper.kiosk_project.dto.CoffeeOrderOptionDto;
import com.shoushoubackenddeveloper.kiosk_project.dto.request.CoffeeOrderOptionPost;
import com.shoushoubackenddeveloper.kiosk_project.dto.request.CoffeeOrderPost;
import com.shoushoubackenddeveloper.kiosk_project.dto.response.SingleResponseDto;
import com.shoushoubackenddeveloper.kiosk_project.service.OrderService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    //쿠키를 사용해 주문내역을 불러온다. ex. 장바구니
    @GetMapping()
    public ResponseEntity getCoffeeOrders(HttpServletRequest request) {
        Cookie cookie = getCookie(request, "DailyCount");
        Integer cookieValue = Integer.valueOf(cookie.getValue());

        List<CoffeeOrderDto> coffeeDtos = orderService.findCoffeeOrders(cookieValue);

        return new ResponseEntity<>(
                new SingleResponseDto<>(coffeeDtos),
                HttpStatus.OK);
    }

    //커피 주문시 실행되는 메소드 커피를 추가한다.
    @PostMapping("/coffee-orders")
    public ResponseEntity postCoffeeOrder(@Valid @RequestBody CoffeeOrderPost coffeeOrderPost) {
        CoffeeOrderDto coffeeOrderDto = orderService.createCoffeeOrder(coffeeOrderPost);

        return new ResponseEntity<>(
                new SingleResponseDto<>(coffeeOrderDto),
                HttpStatus.CREATED);
    }

    //커피 주문 취소
    @DeleteMapping("/coffee-orders/{coffee-orders-id}")
    public ResponseEntity deleteCoffeeOrder(@Positive @PathVariable("coffee-orders-id") Long coffeeOrderId) {
        orderService.deleteCoffee(coffeeOrderId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    // 커피 옵션 추가
    @PostMapping("coffee-orders/{coffee-order-id}/coffee-order-option")
    public ResponseEntity postCoffeeOrderOption(@Valid @RequestBody CoffeeOrderOptionPost coffeeOrderOptionPost) {
        CoffeeOrderOptionDto coffeeOrderOptionDto = orderService.createCoffeeOrderOption(coffeeOrderOptionPost);

        return new ResponseEntity<>(
                new SingleResponseDto<>(coffeeOrderOptionDto),
                HttpStatus.CREATED);
    }

    private Cookie getCookie(HttpServletRequest req, String name) {
        return Optional.ofNullable(req.getCookies())
                .flatMap(cookies -> Arrays.stream(cookies)
                        .filter(c -> c.getName().equals(name))
                        .findFirst())
                .orElse(null);
    }
}
