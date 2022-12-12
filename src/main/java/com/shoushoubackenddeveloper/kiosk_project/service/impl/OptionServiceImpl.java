package com.shoushoubackenddeveloper.kiosk_project.service.impl;

import com.shoushoubackenddeveloper.kiosk_project.dto.CoffeeDto;
import com.shoushoubackenddeveloper.kiosk_project.dto.OptionDto;
import com.shoushoubackenddeveloper.kiosk_project.dto.request.OptionPost;
import com.shoushoubackenddeveloper.kiosk_project.repository.OptionRepository;
import com.shoushoubackenddeveloper.kiosk_project.service.OptionService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OptionServiceImpl implements OptionService {

    private final OptionRepository optionRepository;

    @Override
    public OptionDto findOption(Long optionId) {
        return optionRepository.findById(optionId)
                .map(OptionDto::from)
                .orElseThrow(() -> new EntityNotFoundException("옵션이 없습니다 - coffeeId : " + optionId));
    }

    @Override
    public List<OptionDto> findOptions() {
        return optionRepository.findAll()
                .stream()
                .map(OptionDto::from)
                .toList();
    }

    @Override
    public OptionDto createOption(OptionPost optionPost) {
        verifyExistOption(optionPost.korName());

        return OptionDto.from(optionRepository.save(optionPost.toEntity()));
    }

    public void verifyExistOption(String optionKorName) {
        optionRepository.findByKorName(optionKorName)
                .ifPresent(c -> {
                    throw new IllegalStateException("이미 존재하는 옵션입니다.");
                });
    }
}
