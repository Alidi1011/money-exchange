package com.aarteaga.ms_money_exchange.controller;

import com.aarteaga.ms_money_exchange.dto.GorestUserDto;
import com.aarteaga.ms_money_exchange.dto.UserDto;
import com.aarteaga.ms_money_exchange.service.GorestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.*;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private GorestService gorestService;

    @GetMapping
    public List<GorestUserDto> list() throws IOException {
        return gorestService.findAll();
    }

    @GetMapping("/{id}")
    public GorestUserDto read(@PathVariable String id) throws IOException {
        return gorestService.findById(id);
    }

    @PostMapping
    public UserDto login(@RequestParam("user") String username, @RequestParam("pwd") String password) throws IOException {

        String token = getJWTToken(username);

        //Validate user exist en API free
        GorestUserDto dto = gorestService.findById(username);

        UserDto user = new UserDto();

        if (dto.getId() != null){
            user.setUsername(dto.getId());
            user.setToken(token);
        } else {
            user.setUsername("Invalid User");
            user.setToken("Invalid Access");
        }
        return user;
    }

    private String getJWTToken(String username) {
        String secretKey = "mySecretKey";
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
                        secretKey.getBytes()).compact();

        return token;
    }
}
