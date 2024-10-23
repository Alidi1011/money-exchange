package com.aarteaga.ms_money_exchange.service;

import com.aarteaga.ms_money_exchange.dto.GorestUserDto;

import java.io.IOException;
import java.util.List;

public interface GorestService {
    public List<GorestUserDto> findAll() throws IOException;

    public GorestUserDto findById(String id) throws IOException;
}
