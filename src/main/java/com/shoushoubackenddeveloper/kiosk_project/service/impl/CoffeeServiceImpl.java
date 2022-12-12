package com.shoushoubackenddeveloper.kiosk_project.service.impl;

import com.shoushoubackenddeveloper.kiosk_project.domain.Coffee;
import com.shoushoubackenddeveloper.kiosk_project.dto.CoffeeDto;
import com.shoushoubackenddeveloper.kiosk_project.dto.request.CoffeePost;
import com.shoushoubackenddeveloper.kiosk_project.repository.CoffeeRepository;
import com.shoushoubackenddeveloper.kiosk_project.service.CoffeeService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CoffeeServiceImpl implements CoffeeService {

    private final CoffeeRepository coffeeRepository;

    @Override
    public CoffeeDto findCoffee(Long coffeeId) {
        return coffeeRepository.findById(coffeeId)
                .map(CoffeeDto::from)
                .orElseThrow(() -> new EntityNotFoundException("커피가 없습니다 - coffeeId : " + coffeeId));
    }

    @Override
    public Page<Coffee> findCoffees(Integer page, Integer size) {
        return coffeeRepository.findAll(PageRequest.of(page, size,
                Sort.by("id").descending()));
    }

    @Override
    public CoffeeDto createCoffee(CoffeePost coffeePost) {
        verifyExistCoffee(coffeePost.coffeeCode());

        return CoffeeDto.from(coffeeRepository.save(coffeePost.toEntity()));
    }

    public void verifyExistCoffee(String coffeeCode) {
        coffeeRepository.findByCoffeeCode(coffeeCode)
                .ifPresent( c -> {
                    throw new IllegalStateException("이미 존재하는 커피입니다.");
                });
    }

}
