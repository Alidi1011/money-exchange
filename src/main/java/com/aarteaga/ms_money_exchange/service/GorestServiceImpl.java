package com.aarteaga.ms_money_exchange.service;

import com.aarteaga.ms_money_exchange.dto.GorestUserDto;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class GorestServiceImpl implements GorestService{

    @Value("${gorest.user.url}")
    private String gorestUserUrl;

    @Override
    public List<GorestUserDto> findAll() throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        OkHttpClient http = new OkHttpClient.Builder()
                .hostnameVerifier((hostname, session) -> true)
                .connectTimeout(Long.parseLong("50000"), TimeUnit.SECONDS).build();

        Request request = new Request.Builder().url(gorestUserUrl).get()
                .build();

        Call call = http.newCall(request);
        Response response = call.execute();
        String responseBody = response.body().string();
        response.close();

        List<GorestUserDto> list = mapper.readValue(responseBody, mapper.getTypeFactory().constructCollectionType(List.class, GorestUserDto.class));

        log.info("[RESPONSE] BODY: {}", response.body().toString());

        return list;
    }

    @Override
    public GorestUserDto findById(String id) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        OkHttpClient http = new OkHttpClient.Builder()
                .hostnameVerifier((hostname, session) -> true)
                .connectTimeout(Long.parseLong("50000"), TimeUnit.SECONDS).build();

        Request request = new Request.Builder().url(gorestUserUrl.concat(id)).get()
                .build();

        Call call = http.newCall(request);
        Response response = call.execute();
        String responseBody = response.body().string();
        response.close();

        GorestUserDto gorestUserDto = mapper.readValue(responseBody, GorestUserDto.class);

        log.info("[RESPONSE] BODY: {}", gorestUserDto);

        return gorestUserDto;
    }
}
