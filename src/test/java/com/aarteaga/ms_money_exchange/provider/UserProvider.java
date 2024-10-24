package com.aarteaga.ms_money_exchange.provider;

import com.aarteaga.ms_money_exchange.dto.GorestUserDto;
import java.util.ArrayList;
import java.util.List;

public class UserProvider {
    public static List<GorestUserDto> getUserList(){
        List<GorestUserDto> gorestUserDtoList = new ArrayList<>();
        gorestUserDtoList.add(getUser());
        return gorestUserDtoList;
    }

    public static GorestUserDto getUser() {
        return GorestUserDto.builder()
                .name("Emilio Ramirez")
                .id("12345678")
                .build();
    }
}
