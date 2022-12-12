package com.shoushoubackenddeveloper.kiosk_project.service;

import com.shoushoubackenddeveloper.kiosk_project.domain.Coffee;
import com.shoushoubackenddeveloper.kiosk_project.domain.Option;
import com.shoushoubackenddeveloper.kiosk_project.dto.CoffeeDto;
import com.shoushoubackenddeveloper.kiosk_project.dto.OptionDto;
import com.shoushoubackenddeveloper.kiosk_project.dto.request.OptionPost;
import com.shoushoubackenddeveloper.kiosk_project.repository.OptionRepository;
import com.shoushoubackenddeveloper.kiosk_project.service.impl.OptionServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
public class OptionServiceTest {

    @InjectMocks
    private OptionServiceImpl optionService;
    @Mock
    private OptionRepository optionRepository;

    @DisplayName("옵션아이디로 조회하면, 해당 옵션을 반환한다.")
    @Test
    void findOption() {
        //given
        Long optionId = 1L;
        given(optionRepository.findById(optionId))
                .willReturn(Optional.ofNullable(option1));

        //when
        OptionDto optionDto = optionService.findOption(optionId);

        //then
        assertThat(optionDto.korName())
                .isEqualTo(option1.getKorName());
    }

    @DisplayName("모든 옵션을 조회하면, 옵션리스트를 반환한다.")
    @Test
    void findOptions() {
        List<Option> optionDtos = List.of(option1, option2);

        //given
        given(optionRepository.findAll())
                .willReturn(optionDtos);

        //when
        List<OptionDto> response = optionService.findOptions();

        //then
        assertThat(response)
                .hasSize(optionDtos.size());
        then(optionRepository).should()
                .findAll();
    }

    @DisplayName("옵션 등록 성공.")
    @Test
    void createOption() {
        //given
        given(optionRepository.save(any(Option.class)))
                .willReturn(option1);

        //when
        optionService.createOption(optionPost);

        //then
        then(optionRepository).should()
                .save(any(Option.class));
    }

    private final Option option1 = Option.of(
            1L,
            "시나몬 가루",
            "cinnamon powder",
            500
    );

    private final Option option2 = Option.of(
            2L,
            "초코 가루",
            "chocolate powder",
            500
    );

    private final OptionPost optionPost = OptionPost.of(
            "시나몬 가루",
            "cinnamon powder",
            500
    );
}
