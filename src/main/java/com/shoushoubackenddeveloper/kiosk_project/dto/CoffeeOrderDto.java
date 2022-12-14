package com.shoushoubackenddeveloper.kiosk_project.dto;

import com.shoushoubackenddeveloper.kiosk_project.domain.Coffee;
import com.shoushoubackenddeveloper.kiosk_project.domain.CoffeeOrder;
import com.shoushoubackenddeveloper.kiosk_project.domain.Orders;

public record CoffeeOrderDto(
        Long id,
        Orders order,
        Coffee coffee,
        Integer quantity
) {
    public static CoffeeOrderDto of(Long id, Orders order, Coffee coffee, Integer quantity) {
        return new CoffeeOrderDto(id, order, coffee, quantity);
    }

    public static CoffeeOrderDto from(CoffeeOrder entity) {
        return new CoffeeOrderDto(
                entity.getId(),
                entity.getOrder(),
                entity.getCoffee(),
                entity.getQuantity()
        );
    }

}
