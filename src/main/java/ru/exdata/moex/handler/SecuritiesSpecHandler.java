package ru.exdata.moex.handler;

import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import ru.exdata.moex.db.dao.SecuritiesSpecDao;
import ru.exdata.moex.dto.securitiesSpec.Data;
import ru.exdata.moex.dto.securitiesSpec.DataDescription;
import ru.exdata.moex.dto.securitiesSpec.Row;
import ru.exdata.moex.handler.client.SecuritiesSpecApiClient;
import ru.exdata.moex.mapper.SecuritiesSpecMapper;

import java.util.List;

/**
 * Получение спецификации акции списком.
 */
@Slf4j
@RequiredArgsConstructor
@Singleton
public class SecuritiesSpecHandler {

    private final SecuritiesSpecApiClient securitiesSpecApiClient;
    private final SecuritiesSpecDao securitiesSpecDao;

    public Flux<Row> fetchBySecId(String secId) {
        if (secId == null) {
            return Flux.empty();
        }
        return securitiesSpecDao.findBySecId(secId)
                .map(SecuritiesSpecMapper::fromEntityToDto)
                .switchIfEmpty(
                        fetchSecurities(secId)
                                .doOnNext(it -> securitiesSpecDao.save(it, secId).subscribe())
                );
    }

    private Flux<Row> fetchSecurities(String secId) {
        return securitiesSpecApiClient.fetch(secId)
                .filter(it -> !it.getData().isEmpty())
                .map(it -> getDataById(it.getData(), DataDescription.class))
                .filter(it -> !it.getRows().isEmpty())
                .flatMapIterable(DataDescription::getRows)
                ;
    }

    private <T> T getDataById(List<Data> data, Class<T> clazz) {
        return (T) data.stream().filter(it -> clazz.isInstance(it)).findFirst()
                .orElseThrow();
    }

}
