package com.aarteaga.ms_money_exchange.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class OfferRequestSave {
    @JsonPropertyOrder("user")
    @ApiModelProperty(
            value = "Producto",
            example = "Prestamo",
            dataType = "string",
            notes = "Producto de la oferta",
            required = true,
            position = 1)
    @NotNull
    private String product;
    @NotNull
    private String cardDescription;
    @NotNull
    private String buttonName;
    private String buttonLink;
    private String backgroundImage;
    private String additionalImage;
    private String user;
}
