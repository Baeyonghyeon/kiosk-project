package com.shoushoubackenddeveloper.kiosk_project.service;

import com.shoushoubackenddeveloper.kiosk_project.domain.Coffee;
import com.shoushoubackenddeveloper.kiosk_project.dto.CoffeeDto;
import com.shoushoubackenddeveloper.kiosk_project.dto.request.CoffeePost;
import org.springframework.data.domain.Page;

public interface CoffeeService {

    CoffeeDto findCoffee(Long coffeeId);

    Page<Coffee> findCoffees(Integer page, Integer size);

    CoffeeDto createCoffee(CoffeePost coffeePost);
}
