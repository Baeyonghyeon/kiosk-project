package com.shoushoubackenddeveloper.kiosk_project.dto.request;

import com.shoushoubackenddeveloper.kiosk_project.domain.Coffee;
import com.shoushoubackenddeveloper.kiosk_project.domain.CoffeeOrder;
import com.shoushoubackenddeveloper.kiosk_project.domain.Orders;

// todo : 객체를 받아 저장하기 때문에 controller 에서 post 값을 수정해야 한다. (controller 에서 받는 post 는 객체가 아니라 Integer 값이니까)
public record CoffeeOrderPost(
        Orders order,
        Coffee coffee,
        Integer quantity
) {
    public CoffeeOrder toEntity() {
        return CoffeeOrder.of(
                this.order(),
                this.coffee(),
                this.quantity()
        );
    }
}
