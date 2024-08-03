package ru.exdata.moex.handler;

import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import ru.exdata.moex.db.dao.SecuritiesCandlesDao;
import ru.exdata.moex.db.entity.SecuritiesCandlesAbstract;
import ru.exdata.moex.dto.RequestParamSecuritiesCandles;
import ru.exdata.moex.dto.candles.Data;
import ru.exdata.moex.dto.candles.Document;
import ru.exdata.moex.dto.candles.Row;
import ru.exdata.moex.handler.client.SecuritiesCandlesApiClient;
import ru.exdata.moex.handler.model.PageNumber;
import ru.exdata.moex.mapper.SecuritiesCandlesMapper;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

/**
 * Получение названий акций списком с пагинацией.
 */
@Slf4j
@RequiredArgsConstructor
@Singleton
public class SecuritiesCandlesHandler {

    private final SecuritiesCandlesApiClient securitiesApiClient;
    private final SecuritiesCandlesDao securitiesCandlesDao;
    private final HolidayService holidayService;
    private final int MOEX_RESPONSE_MAX_ROW = 500;

    public Flux<Row> fetch(RequestParamSecuritiesCandles request) {
        holidayService.weekendsIncrement(request);
        validateRequest(request);
        return fetchRepo(request);
    }

    private Flux<Row> fetchRepo(RequestParamSecuritiesCandles request) {
        return Flux.defer(() ->
                        securitiesCandlesDao
                                .findAllByBeginAtAndSecurity(request)
                                .map(it -> SecuritiesCandlesMapper.fromEntityToDtoCandles((SecuritiesCandlesAbstract) it)))
                .switchIfEmpty(fetchAndSave(request))
                .repeatWhen(transactions -> transactions.takeWhile(transactionCount -> {
                                    holidayService.incrementDay(request);
                                    holidayService.weekendsIncrement(request);
                                    return (request.getFrom().isBefore(request.getTill()) || request.getFrom().isEqual(request.getTill()))
                                            && request.getFrom().isBefore(LocalDate.now());
                                }
                        )
                );
    }

    private Flux<Row> fetchAndSave(RequestParamSecuritiesCandles request) {
        PageNumber pageNumber = new PageNumber();
        return Flux.defer(() -> fetchPageable(request, pageNumber)
                        .parallel()
                        .runOn(Schedulers.boundedElastic())
                        .doOnNext(it -> securitiesCandlesDao.save(it, request).subscribe())
                        .sequential()
                )
                .repeatWhen(transactions -> transactions.takeWhile(transactionCount -> pageNumber.get() > 0));
    }

    private Flux<Row> fetchPageable(RequestParamSecuritiesCandles request, PageNumber pageNumber) {
        return securitiesApiClient.fetch(
                        request.getSecurity(),
                        request.getFrom().toString(),
                        request.getTill().toString(),
                        String.valueOf(pageNumber.get()),
                        request.getInterval())
                .filter(it -> !it.getData().getRows().isEmpty())
                .doOnNext(it -> {
                    var firstName = it.getData().getRows();
                    log.debug("SecuritiesHistory: " + firstName.get(0).getBegin());
                    if (it.getData().getRows().size() < MOEX_RESPONSE_MAX_ROW) {
                        pageNumber.stop();
                        holidayService.alignDays(request);
                    } else {
                        pageNumber.increment(MOEX_RESPONSE_MAX_ROW);
                        log.debug("pageNumber: " + pageNumber.get());
                    }
                })
                .switchIfEmpty(Mono.defer(() -> {
                    pageNumber.stop();
                    holidayService.alignDays(request);
                    return Mono.fromCallable(() -> new Document(new Data(List.of(new Row())))).delayElement(Duration.ofSeconds(10L));
                }))
                .flatMapIterable(it -> it.getData().getRows())
                ;
    }

    private void validateRequest(RequestParamSecuritiesCandles request) {
        if (request.getFrom() == null) {
            throw new RuntimeException("Ошибка валидатора запроса. значение from не должно быть null {Например: 2024-06-25}");
        }
        if (request.getTill() == null) {
            throw new RuntimeException("Ошибка валидатора запроса. значение till не должно быть null {Например: 2024-06-25}");
        }
        if (request.getSecurity() == null) {
            throw new RuntimeException("Ошибка валидатора запроса. значение security не должно быть null {Например: SBER}");
        }
        if (request.getSecurity().length() != 4) {
            throw new RuntimeException("Ошибка валидатора запроса. длина security должна быть = 4");
        }
        if (request.getTill().isAfter(LocalDate.now())) {
            throw new RuntimeException("Ошибка валидатора запроса. till should be before now");
        }
        if (!Set.of(1, 10, 60, 24, 7, 31).contains(request.getInterval())) {
            throw new RuntimeException("Ошибка валидатора запроса. Interval should be eq = 1,10,60,24,7,31");
        }
    }

}
