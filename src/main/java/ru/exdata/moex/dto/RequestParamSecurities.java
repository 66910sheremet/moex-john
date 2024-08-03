package ru.exdata.moex.dto;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.core.annotation.Nullable;
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
public class RequestParamSecurities extends GeneralRequest {

    @QueryValue(defaultValue = "1")
    private String isTrading;
    @QueryValue(defaultValue = "stock")
    private String engine;
    @QueryValue(defaultValue = "shares")
    private String market;
    @QueryValue(defaultValue = "0")
    private int start;
    @QueryValue(defaultValue = "ru")
    private String lang;
    /**
     * Поиск инструмента по части Кода, Названию, ISIN, Идентификатору Эмитента, Номеру гос.регистрации.
     * Например: https://iss.moex.com/iss/securities.xml?q=MOEX.
     */
    @Nullable
    @QueryValue
    private String q;
    @Nullable
    @QueryValue(defaultValue = "100")
    private int limit;
    @QueryValue(defaultValue = "trade_engines")
    private String issOnly;

    public String getEngine() {
        return engine.toUpperCase();
    }

    public String getMarket() {
        return market.toUpperCase();
    }

}
