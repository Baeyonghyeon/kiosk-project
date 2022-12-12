package com.shoushoubackenddeveloper.kiosk_project.dto;

import com.shoushoubackenddeveloper.kiosk_project.domain.Coffee;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public record CoffeeDto(
        Long id,
        String coffeeCode,
        String korName,
        String engName,
        Integer price,
        String orderStatus,
        Boolean sizeSelectable,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt
) {
    public static CoffeeDto of(String coffeeCode, String korName, String engName, Integer price, String orderStatus, Boolean sizeSelectable) {
        return new CoffeeDto(null, coffeeCode, korName, engName, price, orderStatus, sizeSelectable, null, null);
    }

    public static CoffeeDto from(Coffee entity) {
        return new CoffeeDto(
                entity.getId(),
                entity.getCoffeeCode(),
                entity.getKorName(),
                entity.getEngName(),
                entity.getPrice(),
                entity.getOrderStatus(),
                entity.getSizeSelectable(),
                entity.getCreatedAt(),
                entity.getModifiedAt()
        );
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
