package ru.exdata.moex.controllers;

import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.RequestBean;
import io.micronaut.http.annotation.Status;
import io.micronaut.validation.Validated;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import ru.exdata.moex.dto.RequestParamSecurities;
import ru.exdata.moex.handler.SecuritiesHandler;

@Validated
@RequiredArgsConstructor
@Controller("/iss/securities")
public class SecuritiesRouter {

    private final SecuritiesHandler securitiesService;

    @Get
    @Status(HttpStatus.OK)
    public Flux<Object[]> getSecurities(@Valid @RequestBean RequestParamSecurities request) {
        return securitiesService.fetch(request);
    }

}
