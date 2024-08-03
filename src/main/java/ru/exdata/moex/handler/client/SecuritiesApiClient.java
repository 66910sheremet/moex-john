package ru.exdata.moex.handler.client;

import io.micronaut.core.annotation.Nullable;
import io.micronaut.core.async.annotation.SingleResult;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Header;
import io.micronaut.http.annotation.QueryValue;
import io.micronaut.http.client.annotation.Client;
import reactor.core.publisher.Mono;
import ru.exdata.moex.dto.securities.Document;

import static io.micronaut.http.HttpHeaders.ACCEPT;
import static io.micronaut.http.HttpHeaders.USER_AGENT;

@Client(id = "moex")
@Header(name = USER_AGENT, value = "Micronaut HTTP Client")
@Header(name = ACCEPT, value = MediaType.APPLICATION_JSON)
public interface SecuritiesApiClient {

    @Get("/iss/securities")
    @SingleResult
    Mono<Document> fetch(@QueryValue(defaultValue = "1") String isTrading,
                         @QueryValue(defaultValue = "stock") String engine,
                         @QueryValue(defaultValue = "shares") String market,
                         @QueryValue(defaultValue = "0") int start,
                         @QueryValue(defaultValue = "ru") String lang,
                         /**
                          * Поиск инструмента по части Кода, Названию, ISIN, Идентификатору Эмитента, Номеру гос.регистрации.
                          * Например: https://iss.moex.com/iss/securities.xml?q=MOEX.
                          */
                         @Nullable @QueryValue String q,
                         @Nullable @QueryValue(defaultValue = "100") String limit,
                         @QueryValue(defaultValue = "trade_engines") String issOnly);

}
