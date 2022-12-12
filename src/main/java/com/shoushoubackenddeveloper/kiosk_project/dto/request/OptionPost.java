package com.shoushoubackenddeveloper.kiosk_project.dto.request;

import com.shoushoubackenddeveloper.kiosk_project.domain.Option;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record OptionPost(@NotBlank String korName,
                         String engName,
                         @Positive Integer price
) {
    public static OptionPost of(String korName, String engName, Integer price) {
        return new OptionPost(korName, engName, price);
    }

    public Option toEntity() {
        return Option.of(
                this.korName(),
                this.engName(),
                this.price()
        );
    }
}
