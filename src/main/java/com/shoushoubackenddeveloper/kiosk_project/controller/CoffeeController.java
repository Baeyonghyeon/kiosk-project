package com.shoushoubackenddeveloper.kiosk_project.controller;

import com.shoushoubackenddeveloper.kiosk_project.domain.Coffee;
import com.shoushoubackenddeveloper.kiosk_project.dto.CoffeeDto;
import com.shoushoubackenddeveloper.kiosk_project.dto.response.MultiResponseDto;
import com.shoushoubackenddeveloper.kiosk_project.dto.response.SingleResponseDto;
import com.shoushoubackenddeveloper.kiosk_project.service.CoffeeService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/coffees")
public class CoffeeController {

    private final CoffeeService coffeeService;

    @GetMapping("/{coffee-id}")
    public ResponseEntity getCoffee(@Positive @PathVariable("coffee-id") Long coffeeId) {
        CoffeeDto coffeeDto = coffeeService.findCoffee(coffeeId);

        return new ResponseEntity<>(
                new SingleResponseDto<>(coffeeDto),
                HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity getCoffees(@Positive @RequestParam Integer size,
                                     @Positive @RequestParam Integer page) {
        Page<Coffee> coffeePage = coffeeService.findCoffees(page - 1, size);
        List<CoffeeDto> coffeeDtos = coffeePage.getContent().stream()
                .map(CoffeeDto::from)
                .toList();

        return new ResponseEntity<>(
                new MultiResponseDto<>(coffeeDtos, coffeePage),
                HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity postCoffee(@Valid @RequestBody CoffeeDto coffeeDto){
        CoffeeDto createCoffeeDto = coffeeService.createCoffee(coffeeDto);

        return new ResponseEntity<>(
                new SingleResponseDto<>(createCoffeeDto),
                HttpStatus.CREATED);
    }
}
