package com.shoushoubackenddeveloper.kiosk_project.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.shoushoubackenddeveloper.kiosk_project.config.LocalDateTimeSerializer;
import com.shoushoubackenddeveloper.kiosk_project.dto.OptionDto;
import com.shoushoubackenddeveloper.kiosk_project.dto.request.OptionPost;
import com.shoushoubackenddeveloper.kiosk_project.service.OptionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@WebMvcTest(OptionController.class)
@MockBean(JpaMetamodelMappingContext.class)
public class OptionControllerTest {

    private final MockMvc mockMvc;
    @MockBean
    private OptionService optionService;
    @Autowired
    private Gson gson;

    public OptionControllerTest(@Autowired MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @BeforeEach
    void init() {
        //Localdatetime format에러 셋팅
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeSerializer());
        gson = gsonBuilder.setPrettyPrinting().create();
    }

    @DisplayName("단일 옵션정보 불러오기 - 정상호출")
    @Test
    void getOption() throws Exception {
        //given
        given(optionService.findOption(anyLong()))
                .willReturn(optionDto1);

        String content = gson.toJson(optionDto1);

        //when
        ResultActions actions =
                mockMvc.perform(get("/options/{option-id}", anyLong())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content));

        //then
        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$.data.korName").value(optionDto1.korName()))
                .andExpect(jsonPath("$.data.engName").value(optionDto1.engName()));
    }

    @DisplayName("옵션리스트 정보 불러오기 - 정상호출")
    @Test
    void getOptions() throws Exception {
        List<OptionDto> response = List.of(optionDto1, optionDto2);
        //given
        given(optionService.findOptions())
                .willReturn(response);

        String content = gson.toJson(response);

        //when
        ResultActions actions =
                mockMvc.perform(get("/options")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content));

        //then
        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$.data.size()").value(response.size()));
    }

    @DisplayName("옵션정보 추가 - 정상호출")
    @Test
    void postCoffee() throws Exception {
        //given
        given(optionService.createOption(any(OptionPost.class)))
                .willReturn(optionDto1);

        String content = gson.toJson(optionDto1);

        //when
        ResultActions actions =
                mockMvc.perform(post("/options")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content));

        //then
        actions.andExpect(status().isCreated())
                .andExpect(jsonPath("$.data.id").value(optionDto1.id()))
                .andExpect(jsonPath("$.data.korName").value(optionDto1.korName()))
                .andExpect(jsonPath("$.data.engName").value(optionDto1.engName()));
    }

    private final OptionDto optionDto1 = OptionDto.of(
            1L,
            "시나몬 가루",
            "cinnamon powder",
            500
    );

    private final OptionDto optionDto2 = OptionDto.of(
            2L,
            "초코 가루",
            "chocolate powder",
            500
    );
}
