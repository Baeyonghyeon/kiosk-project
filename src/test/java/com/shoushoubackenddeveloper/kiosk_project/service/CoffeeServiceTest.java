package com.shoushoubackenddeveloper.kiosk_project.service;

import com.shoushoubackenddeveloper.kiosk_project.domain.Coffee;
import com.shoushoubackenddeveloper.kiosk_project.dto.CoffeeDto;
import com.shoushoubackenddeveloper.kiosk_project.dto.request.CoffeePost;
import com.shoushoubackenddeveloper.kiosk_project.repository.CoffeeRepository;
import com.shoushoubackenddeveloper.kiosk_project.service.impl.CoffeeServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class CoffeeServiceTest {

    @InjectMocks
    private CoffeeServiceImpl coffeeService;
    @Mock
    private CoffeeRepository coffeeRepository;

    @DisplayName("커피아이디로 조회하면, 해당 커피를 반환한다.")
    @Test
    void findCoffee() {
        //given
        Long coffeeId = 1L;
        given(coffeeRepository.findById(coffeeId))
                .willReturn(Optional.ofNullable(coffee));

        //when
        CoffeeDto coffeeDto = coffeeService.findCoffee(coffeeId);

        //then
        assertThat(coffeeDto.coffeeCode())
                .isEqualTo(coffee.getCoffeeCode());
    }

    @DisplayName("모든 커피를 조회하면, 페이지<커피>를 반환한다.")
    @Test
    void findCoffees() {
        //given
        given(coffeeRepository.findAll(any(PageRequest.class)))
                .willReturn(Page.empty());

        //when
        Page<Coffee> coffees = coffeeService.findCoffees(1, 10);

        //then
        assertThat(coffees)
                .isEmpty();
        then(coffeeRepository).should()
                .findAll(any(PageRequest.class));
    }

    @DisplayName("커피 등록 성공.")
    @Test
    void createCoffee() {
        //given
        given(coffeeRepository.save(any(Coffee.class)))
                .willReturn(coffee);

        //when
        coffeeService.createCoffee(coffeePost);

        //then
        then(coffeeRepository).should()
                .save(any(Coffee.class));
    }

    private final Coffee coffee = Coffee.of(
            1L,
            "h_101",
            "카푸치노",
            "Cappuccino",
            4700,
            "판매중",
            true);

    private final CoffeePost coffeePost = CoffeePost.of(
            "h_101",
            "카푸치노",
            "Cappuccino",
            4700,
            "판매중",
            true);
}