package com.shoushoubackenddeveloper.kiosk_project.dto;

import com.shoushoubackenddeveloper.kiosk_project.domain.Option;

public record OptionDto(
        Long id,
        String korName,
        String engName,
        Integer price
) {

    public static OptionDto of(Long id, String korName, String engName, Integer price) {
        return new OptionDto(id, korName, engName, price);
    }

    public static OptionDto from(Option entity){
        return new OptionDto(
                entity.getId(),
                entity.getKorName(),
                entity.getEngName(),
                entity.getPrice()
        );
    }

}
