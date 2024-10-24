package com.aarteaga.ms_money_exchange.controller;

import com.aarteaga.ms_money_exchange.dto.GorestUserDto;
import com.aarteaga.ms_money_exchange.provider.UserProvider;
import com.aarteaga.ms_money_exchange.service.GorestService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.io.IOException;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerTest {

    @MockBean
    GorestService gorestService;

    @Autowired
    private WebTestClient webClient;

    @BeforeEach
    void setUp() {
    }

    @Test
    @DisplayName("Get all users from Gorest API")
    void list() throws IOException {

        Mockito.when(gorestService.findAll())
                .thenReturn((UserProvider.getUserList()));

        webClient.get()
                .uri("/user")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
                .expectBodyList(GorestUserDto.class)
                .consumeWith(response -> {
                    List<GorestUserDto> gorestUserDtos = response.getResponseBody();
                    gorestUserDtos.forEach(c -> {
                        System.out.println(c.getName());
                    });
                    Assertions.assertThat(gorestUserDtos.size() > 0).isTrue();
                });
        //.hasSize(1);
    }

    @Test
    @DisplayName("Read User By Id")
    void read() throws IOException {
        Mockito.when(gorestService.findById(Mockito.anyString()))
                .thenReturn(UserProvider.getUser());

        webClient.get().uri("/user/1")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
                .expectBody()
                .jsonPath("$.id").isNotEmpty()
                .jsonPath("$.name").isEqualTo(UserProvider.getUser().getName());
    }

    @Test
    void login() throws IOException {
        Mockito.when(gorestService.findById(Mockito.anyString()))
                .thenReturn(UserProvider.getUser());

        webClient.post().uri("/user?user=12345678&pwd=123456")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
                .expectBody()
                .jsonPath("$.token").isNotEmpty()
                .jsonPath("$.username").isEqualTo("12345678");

    }
}