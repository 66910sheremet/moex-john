package ru.exdata.moex.db.dao;

import io.micronaut.transaction.annotation.Transactional;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.exdata.moex.db.entity.*;
import ru.exdata.moex.db.repository.*;
import ru.exdata.moex.dto.RequestParamSecuritiesCandles;
import ru.exdata.moex.dto.candles.Row;
import ru.exdata.moex.enums.Interval;
import ru.exdata.moex.mapper.MapperUtils;
import ru.exdata.moex.mapper.SecuritiesCandlesMapper;

import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@Singleton
public class SecuritiesCandlesDao {

    private final SecuritiesCandlesM1Repository securitiesCandlesM1Repository;
    private final SecuritiesCandlesM10Repository securitiesCandlesM10Repository;
    private final SecuritiesCandlesM60Repository securitiesCandlesM60Repository;
    private final SecuritiesCandlesD1Repository securitiesCandlesD1Repository;
    private final SecuritiesCandlesD7Repository securitiesCandlesD7Repository;
    private final SecuritiesCandlesD31Repository securitiesCandlesD31Repository;

    @Transactional
    public <T, S extends Flux<T>> S findAllByBeginAtAndSecurity(RequestParamSecuritiesCandles request) {
        log.info("fetch candles from db. start:{} finish:{}", request.getFrom(), request.getTill());
        return switch (Objects.requireNonNull(Interval.valueOf(request.getInterval()))) {
            case M1 -> (S) securitiesCandlesM1Repository.findAllByBeginAtAndSecurity(
                    request.getFrom(),
                    request.getSecurity());
            case M10 -> (S) securitiesCandlesM10Repository.findAllByBeginAtAndSecurity(
                    request.getFrom(),
                    request.getSecurity());
            case M60 -> (S) securitiesCandlesM60Repository.findAllByBeginAtAndSecurity(
                    request.getFrom(),
                    request.getSecurity());
            case D1 -> (S) securitiesCandlesD1Repository.findAllByBeginAtAndSecurity(
                    request.getFrom(),
                    request.getSecurity());
            case D7 -> (S) securitiesCandlesD7Repository.findAllByBeginAtAndSecurity(
                    request.getFrom(),
                    request.getSecurity());
            case D31 -> (S) securitiesCandlesD31Repository.findAllByBeginAtAndSecurity(
                    request.getFrom(),
                    request.getSecurity());
            default -> throw new RuntimeException("Not defined time frame");
        };
    }

    @Transactional
    public <T, S extends Mono<T>> S save(Row row, RequestParamSecuritiesCandles request) {
        if (row == null || row.getBegin() == null || row.getEnd() == null || request.getSecurity() == null) {
            return (S) Mono.just(new SecuritiesCandlesM1());
        }
        var begin = row.getBegin();
        var end = row.getEnd();
        return switch (Objects.requireNonNull(Interval.valueOf(request.getInterval()))) {
            case M1 -> (S) securitiesCandlesM1Repository.findByPk(
                            request.getSecurity(),
                            begin,
                            end)
                    .switchIfEmpty(securitiesCandlesM1Repository.save(SecuritiesCandlesMapper.fromArrToEntity(row, request, new SecuritiesCandlesM1())));
            case M10 -> (S) securitiesCandlesM10Repository.findByPk(
                            request.getSecurity(),
                            begin,
                            end)
                    .switchIfEmpty(securitiesCandlesM10Repository.save(SecuritiesCandlesMapper.fromArrToEntity(row, request, new SecuritiesCandlesM10())));
            case M60 -> (S) securitiesCandlesM60Repository.findByPk(
                            request.getSecurity(),
                            begin,
                            end)
                    .switchIfEmpty(securitiesCandlesM60Repository.save(SecuritiesCandlesMapper.fromArrToEntity(row, request, new SecuritiesCandlesM60())));
            case D1 -> (S) securitiesCandlesD1Repository.findByPk(
                            request.getSecurity(),
                            begin,
                            end)
                    .switchIfEmpty(securitiesCandlesD1Repository.save(SecuritiesCandlesMapper.fromArrToEntity(row, request, new SecuritiesCandlesD1())));
            case D7 -> (S) securitiesCandlesD7Repository.findByPk(
                            request.getSecurity(),
                            begin,
                            end)
                    .switchIfEmpty(securitiesCandlesD7Repository.save(SecuritiesCandlesMapper.fromArrToEntity(row, request, new SecuritiesCandlesD7())));
            case D31 -> (S) securitiesCandlesD31Repository.findByPk(
                            request.getSecurity(),
                            begin,
                            end)
                    .switchIfEmpty(securitiesCandlesD31Repository.save(SecuritiesCandlesMapper.fromArrToEntity(row, request, new SecuritiesCandlesD31())));
            default -> throw new RuntimeException("Not defined time frame");
        };
    }

}
