package com.aarteaga.ms_money_exchange.controller;

import com.aarteaga.ms_money_exchange.entity.Exchange;
import com.aarteaga.ms_money_exchange.provider.ExchangeProvider;
import com.aarteaga.ms_money_exchange.service.ExchangeService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ExchangeControllerTest {

    @MockBean
    ExchangeService exchangeService;

    @Autowired
    private WebTestClient webClient;

    @BeforeEach
    void setUp() {
    }

    @Test
    @DisplayName("Get all exchanges")
    void findAll() {

        Mockito.when(exchangeService.findAll())
                .thenReturn(Flux.fromIterable(ExchangeProvider.getExchangeList()));

        webClient.get()
                .uri("/exchange")
                .header("Authorization", "Bearer " + this.getJWTToken("12345678"))
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
                .expectBodyList(Exchange.class)
                .consumeWith(response -> {
                    List<Exchange> exchangeList = response.getResponseBody();
                    exchangeList.forEach(c -> {
                        System.out.println(c.getOriginCurrency());
                    });
                    Assertions.assertThat(exchangeList.size() > 0).isTrue();
                });
        //.hasSize(1);
    }

    private String getJWTToken(String username) {
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");

        String token = Jwts
                .builder()
                .setId("aarteagaJWT")
                .setSubject(username)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 300000))
                .signWith(SignatureAlgorithm.HS512,
                        "mySecretKey".getBytes()).compact();

        return token;
    }
}