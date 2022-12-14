package com.shoushoubackenddeveloper.kiosk_project.dto.request;

import com.shoushoubackenddeveloper.kiosk_project.domain.CoffeeOrder;
import com.shoushoubackenddeveloper.kiosk_project.domain.CoffeeOrderOption;
import com.shoushoubackenddeveloper.kiosk_project.domain.Option;

// todo : 객체를 받아 저장하기 때문에 controller 에서 post 값을 수정해야 한다. (controller 에서 받는 post 는 객체가 아니라 Integer 값이니까)
public record CoffeeOrderOptionPost(
        Option option,
        CoffeeOrder coffeeOrder,
        Integer quantity
) {
    public CoffeeOrderOption toEntity() {
        return null;
    }
}
