package com.shoushoubackenddeveloper.kiosk_project.service;

import com.shoushoubackenddeveloper.kiosk_project.domain.Coffee;
import com.shoushoubackenddeveloper.kiosk_project.dto.CoffeeDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CoffeeService {

    CoffeeDto findCoffee(Long coffeeId);

    Page<Coffee> findCoffees(Integer page, Integer size);

    public CoffeeDto createCoffee(CoffeeDto coffeeDto);
}
