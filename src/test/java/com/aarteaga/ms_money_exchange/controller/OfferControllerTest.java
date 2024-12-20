package com.aarteaga.ms_money_exchange.controller;

import com.aarteaga.ms_money_exchange.entity.Exchange;
import com.aarteaga.ms_money_exchange.model.OfferDetail;
import com.aarteaga.ms_money_exchange.provider.ExchangeProvider;
import com.aarteaga.ms_money_exchange.service.ExchangeService;
import com.aarteaga.ms_money_exchange.service.OfferService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.assertj.core.api.Assertions;
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OfferControllerTest {

    @MockBean
    OfferService offerService;

    @Autowired
    private WebTestClient webClient;

    public static OfferDetail getOffer() {
        return OfferDetail.builder()
                .id(1)
                .cardDescription("Description")
                .build();
    }

    @Test
    @DisplayName("Get all offers")
    void findAll() {
        List<OfferDetail> offerDetailList = new ArrayList<>();
        offerDetailList.add(getOffer());

        Mockito.when(offerService.findAll())
                .thenReturn(Flux.fromIterable(offerDetailList));

        webClient.get()
                .uri("/offers/retrieve")
                .header("Authorization", "Bearer " + this.getJWTToken("12345678"))
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
                .expectBodyList(OfferDetail.class)
                .consumeWith(response -> {
                    List<OfferDetail> offerList = response.getResponseBody();
                    offerList.forEach(c -> {
                        System.out.println(c.getCardDescription());
                    });
                    Assertions.assertThat(!offerDetailList.isEmpty()).isTrue();
                });
        //.hasSize(1);
    }

    @Test
    void create() {
    }

    @Test
    void update() {
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