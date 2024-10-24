package com.aarteaga.ms_money_exchange.controller;

import com.aarteaga.ms_money_exchange.dto.GorestUserDto;
import com.aarteaga.ms_money_exchange.dto.UserDto;
import com.aarteaga.ms_money_exchange.service.GorestService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.*;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "User", description = "Operations for USER")
@RestController
@RequestMapping("/user")
public class UserController {

    @Value("${jwt.secret.key}")
    private String jwtSecretKey;

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
                        jwtSecretKey.getBytes()).compact();

        return token;
    }
}
