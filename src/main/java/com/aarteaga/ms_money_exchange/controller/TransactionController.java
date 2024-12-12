package com.aarteaga.ms_money_exchange.controller;

import com.aarteaga.ms_money_exchange.entity.TransactionExchange;
import com.aarteaga.ms_money_exchange.service.TransactionService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Tag(name = "Transaction", description = "Transaction Management API")
@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    private final String HEADER = "Authorization";
    private final String PREFIX = "Bearer ";
    private final String SECRET = "mySecretKey";

    @PostMapping
    public Mono<ResponseEntity<TransactionExchange>> create(HttpServletRequest request, @RequestBody TransactionExchange transactionExchange) {
        String jwtToken = request.getHeader(HEADER).replace(PREFIX, "");
        Claims claims = Jwts.parser().setSigningKey(SECRET.getBytes()).parseClaimsJws(jwtToken).getBody();
        log.info("Authorization GET USERNAME: "+ claims.getSubject());
        return transactionService.create(transactionExchange)
                .map(te -> ResponseEntity.ok().body(te));
    }

    @GetMapping
    public Mono<ResponseEntity<Flux<TransactionExchange>>> findAll() {
        return Mono.just(ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(transactionService.findAll()));
    }
}

