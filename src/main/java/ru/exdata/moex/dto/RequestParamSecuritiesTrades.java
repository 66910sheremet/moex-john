package ru.exdata.moex.dto;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.QueryValue;
import io.micronaut.serde.annotation.Serdeable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = false)
@Introspected(excludes = "duration")
@Serdeable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestParamSecuritiesTrades extends GeneralRequest {

    @QueryValue(defaultValue = "stock")
    private String engine;
    @QueryValue(defaultValue = "shares")
    private String market;
    @PathVariable
    private String security;

    @QueryValue(defaultValue = "0")
    private long tradeno;
    @QueryValue(defaultValue = "0")
    private int start;

    public String getEngine() {
        return engine.toUpperCase();
    }

    public String getMarket() {
        return market.toUpperCase();
    }

    public String getSecurity() {
        return security.toUpperCase();
    }

}
