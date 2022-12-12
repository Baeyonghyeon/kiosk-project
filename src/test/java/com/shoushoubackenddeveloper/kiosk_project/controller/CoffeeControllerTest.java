package com.shoushoubackenddeveloper.kiosk_project.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.shoushoubackenddeveloper.kiosk_project.config.LocalDateTimeSerializer;
import com.shoushoubackenddeveloper.kiosk_project.domain.Coffee;
import com.shoushoubackenddeveloper.kiosk_project.dto.CoffeeDto;
import com.shoushoubackenddeveloper.kiosk_project.dto.request.CoffeePost;
import com.shoushoubackenddeveloper.kiosk_project.service.CoffeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@ActiveProfiles("test")
@WebMvcTest(CoffeeController.class)
@MockBean(JpaMetamodelMappingContext.class)
class CoffeeControllerTest {

    private final MockMvc mockMvc;
    @MockBean
    private CoffeeService coffeeService;
    @Autowired
    private Gson gson;

    CoffeeControllerTest(@Autowired MockMvc mvc) {
        this.mockMvc = mvc;
    }

    @BeforeEach
    void init() {
        //Localdatetime format에러 셋팅
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeSerializer());
        gson = gsonBuilder.setPrettyPrinting().create();
    }

    @DisplayName("단일 커피정보 불러오기 - 정상호출")
    @Test
    void getCoffee() throws Exception {
        //given
        given(coffeeService.findCoffee(anyLong()))
                .willReturn(coffeeDto);

        String content = gson.toJson(coffeeDto);

        //when
        ResultActions actions =
                mockMvc.perform(get("/coffees/{coffee-id}", anyLong())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content));

        //then
        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$.data.coffeeCode").value(coffeeDto.coffeeCode()))
                .andExpect(jsonPath("$.data.korName").value(coffeeDto.korName()))
                .andExpect(jsonPath("$.data.engName").value(coffeeDto.engName()));
    }

    @Disabled("controller MultiResponseDto 형태에 맞춰 content를 만들어 줘야한다.")
    @DisplayName("페이지, 커피리스트 정보 불러오기 - 정상호출")
    @Test
    void getCoffees() throws Exception {
        //given
        Pageable pageable = PageRequest.of(1, 10);
        Page<Coffee> pageCoffees = new PageImpl<>(List.of(coffee1, coffee2), pageable, 2L);

        given(coffeeService.findCoffees(anyInt(), anyInt()))
                .willReturn(pageCoffees);

        //when
        ResultActions actions =
                mockMvc.perform(get("/coffees")
                        .param("page", "1")
                        .param("size", "10")
                        .accept(MediaType.APPLICATION_JSON));

        //then
        actions.andExpect(status().isOk());
    }

    @DisplayName("커피정보 추가 - 정상호출")
    @Test
    void postCoffee() throws Exception {
        //given
        given(coffeeService.createCoffee(any(CoffeePost.class)))
                .willReturn(coffeeDto);

        String content = gson.toJson(coffeeDto);

        //when
        ResultActions actions =
                mockMvc.perform(post("/coffees")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content));

        //then
        actions.andExpect(status().isCreated())
                .andExpect(jsonPath("$.data.coffeeCode").value(coffeeDto.coffeeCode()))
                .andExpect(jsonPath("$.data.korName").value(coffeeDto.korName()))
                .andExpect(jsonPath("$.data.engName").value(coffeeDto.engName()));
    }

    private final CoffeeDto coffeeDto = CoffeeDto.of(
            "h_101",
            "카푸치노",
            "Cappuccino",
            4700,
            "판매중",
            true);

    private final Coffee coffee1 = Coffee.of(
            1L,
            "h_101",
            "카푸치노",
            "Cappuccino",
            4700,
            "판매중",
            true);

    private final Coffee coffee2 = Coffee.of(
            2L,
            "h_102",
            "아메리카노",
            "Americano",
            4500,
            "판매중",
            true);
}