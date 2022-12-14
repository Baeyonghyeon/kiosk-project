package com.shoushoubackenddeveloper.kiosk_project.service.impl;

import com.shoushoubackenddeveloper.kiosk_project.domain.CoffeeOrderOption;
import com.shoushoubackenddeveloper.kiosk_project.domain.Orders;
import com.shoushoubackenddeveloper.kiosk_project.dto.CoffeeOrderDto;
import com.shoushoubackenddeveloper.kiosk_project.dto.CoffeeOrderOptionDto;
import com.shoushoubackenddeveloper.kiosk_project.dto.request.CoffeeOrderOptionPost;
import com.shoushoubackenddeveloper.kiosk_project.dto.request.CoffeeOrderPost;
import com.shoushoubackenddeveloper.kiosk_project.repository.CoffeeOrderOptionRepository;
import com.shoushoubackenddeveloper.kiosk_project.repository.CoffeeOrderRepository;
import com.shoushoubackenddeveloper.kiosk_project.repository.OrderRepository;
import com.shoushoubackenddeveloper.kiosk_project.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CoffeeOrderRepository coffeeOrderRepository;
    private final CoffeeOrderOptionRepository coffeeOrderOptionRepository;
    private Integer orderNo = 0;

    @Override
    public Orders createOrder() {
        orderNoIncrease();

        return orderRepository.save(Orders.of(this.orderNo));
    }

    @Override
    public List<CoffeeOrderDto> findCoffeeOrders(Integer orderId) {
        return coffeeOrderRepository.findAll().stream()
                .map(CoffeeOrderDto::from)
                .toList();
    }

    @Override
    public CoffeeOrderDto createCoffeeOrder(CoffeeOrderPost coffeeOrderPost) {
        return CoffeeOrderDto.from(coffeeOrderRepository.save(coffeeOrderPost.toEntity()));
    }

    @Override
    public void deleteCoffee(Long coffeeOrderId) {
        coffeeOrderRepository.deleteById(coffeeOrderId);
    }

    @Override
    public CoffeeOrderOptionDto createCoffeeOrderOption(CoffeeOrderOptionPost coffeeOrderOptionPost) {
        CoffeeOrderOption coffeeOrderOption = coffeeOrderOptionRepository.save(coffeeOrderOptionPost.toEntity());

        return null;
//        return CoffeeOrderOptionDto.from(coffeeOrderOption);
    }

    private void orderNoIncrease() {
        this.orderNo++;
    }

}
