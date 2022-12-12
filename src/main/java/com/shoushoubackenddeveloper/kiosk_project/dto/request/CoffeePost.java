package com.shoushoubackenddeveloper.kiosk_project.dto.request;

import com.shoushoubackenddeveloper.kiosk_project.domain.Coffee;
import com.shoushoubackenddeveloper.kiosk_project.dto.CoffeeDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record CoffeePost(@NotBlank String coffeeCode,
                         @NotBlank String korName,
                         String engName,
                         @Positive Integer price,
                         @NotBlank String orderStatus,
                         Boolean sizeSelectable) {

    public static CoffeePost of(String coffeeCode, String korName, String engName, Integer price, String orderStatus, Boolean sizeSelectable) {
        return new CoffeePost(coffeeCode, korName, engName, price, orderStatus, sizeSelectable);
    }

    public Coffee toEntity() {
        return Coffee.of(
                this.coffeeCode(),
                this.korName(),
                this.engName(),
                this.price(),
                this.orderStatus(),
                this.sizeSelectable()
        );
    }

}
