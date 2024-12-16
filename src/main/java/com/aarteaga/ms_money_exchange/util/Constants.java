package com.aarteaga.ms_money_exchange.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Constants {

    public static final String USER_BACK = "backSystem";
    public static final String UPDATE_ACTION = "update";
    public static final String CREATE_ACTION = "create";

    public static final String OPERATION_STATUS_OK = "00";
    public static final String OPERATION_STATUS_OK_MESSAGE = "Operación exitosa";
    public static final String OPERATION_STATUS_ERROR = "01";
    public static final String OPERATION_STATUS_ERROR_MESSAGE = "Operación con error";
}
