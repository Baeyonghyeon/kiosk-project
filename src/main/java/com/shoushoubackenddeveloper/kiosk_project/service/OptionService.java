package com.shoushoubackenddeveloper.kiosk_project.service;

import com.shoushoubackenddeveloper.kiosk_project.dto.OptionDto;
import com.shoushoubackenddeveloper.kiosk_project.dto.request.OptionPost;

import java.util.List;

public interface OptionService {

    OptionDto findOption(Long optionId);

    List<OptionDto> findOptions();

    OptionDto createOption(OptionPost optionPost);
}
