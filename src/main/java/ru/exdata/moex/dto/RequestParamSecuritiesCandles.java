package ru.exdata.moex.dto;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.core.convert.format.Format;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.QueryValue;
import io.micronaut.serde.annotation.Serdeable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = false)
@Introspected(excludes = "duration")
@Serdeable
@Data
@NoArgsConstructor
public class RequestParamSecuritiesCandles extends GeneralRequest {

    private final static String FORMAT_DATE = "yyyy-MM-dd";

    public RequestParamSecuritiesCandles(String security, LocalDate from, LocalDate till, Integer interval) {
        super();
        this.security = security;
        this.from = from;
        this.till = till;
        this.interval = interval;
    }

    @PathVariable
    private String security;
    @PathVariable
    private String board;
    @Format(FORMAT_DATE)
    @QueryValue
    private LocalDate from;
    @Format(FORMAT_DATE)
    @QueryValue
    private LocalDate till;
    @QueryValue
    private Integer interval;

    public String getSecurity() {
        return security.toUpperCase();
    }

}
