package com.shoushoubackenddeveloper.kiosk_project.service;

import com.shoushoubackenddeveloper.kiosk_project.domain.Orders;
import com.shoushoubackenddeveloper.kiosk_project.dto.CoffeeOrderDto;
import com.shoushoubackenddeveloper.kiosk_project.dto.CoffeeOrderOptionDto;
import com.shoushoubackenddeveloper.kiosk_project.dto.request.CoffeeOrderOptionPost;
import com.shoushoubackenddeveloper.kiosk_project.dto.request.CoffeeOrderPost;

import java.util.List;

public interface OrderService {

    Orders createOrder();

    List<CoffeeOrderDto> findCoffeeOrders(Integer orderId);

    CoffeeOrderDto createCoffeeOrder(CoffeeOrderPost coffeeOrderPost);

    void deleteCoffee(Long coffeeOrderId);

    CoffeeOrderOptionDto createCoffeeOrderOption(CoffeeOrderOptionPost coffeeOrderOptionPost);
}
