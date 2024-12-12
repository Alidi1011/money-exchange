package com.aarteaga.ms_money_exchange.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Utility {

    public static LocalDateTime getLocalDateTime() {
        return LocalDateTime.now();
    }
}
