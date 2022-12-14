package com.shoushoubackenddeveloper.kiosk_project.controller;

import com.shoushoubackenddeveloper.kiosk_project.dto.OptionDto;
import com.shoushoubackenddeveloper.kiosk_project.dto.request.OptionPost;
import com.shoushoubackenddeveloper.kiosk_project.dto.response.SingleResponseDto;
import com.shoushoubackenddeveloper.kiosk_project.service.OptionService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/options")
public class OptionController {

    private final OptionService optionService;

    @GetMapping("/{option-id}")
    public ResponseEntity getCoffee(@Positive @PathVariable("option-id") Long optionId) {
        OptionDto optionDto = optionService.findOption(optionId);

        return new ResponseEntity<>(
                new SingleResponseDto<>(optionDto),
                HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity getCoffees() {
        List<OptionDto> optionDtos = optionService.findOptions();

        return new ResponseEntity<>(
                new SingleResponseDto<>(optionDtos),
                HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity postOption(@Valid @RequestBody OptionPost optionPost){
        OptionDto optionDto = optionService.createOption(optionPost);

        return new ResponseEntity<>(
                new SingleResponseDto<>(optionDto),
                HttpStatus.CREATED);
    }
}
