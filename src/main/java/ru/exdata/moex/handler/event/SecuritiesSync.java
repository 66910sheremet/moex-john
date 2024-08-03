package ru.exdata.moex.handler.event;

import io.micronaut.context.event.StartupEvent;
import io.micronaut.runtime.event.annotation.EventListener;
import io.micronaut.transaction.annotation.Transactional;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import ru.exdata.moex.db.entity.MoexSync;
import ru.exdata.moex.db.repository.MoexSyncRepository;
import ru.exdata.moex.dto.RequestParamSecurities;
import ru.exdata.moex.dto.securities.Row;
import ru.exdata.moex.handler.SecuritiesHandler;
import ru.exdata.moex.handler.SecuritiesSpecHandler;

import java.time.Duration;
import java.time.LocalDateTime;

@Slf4j
@RequiredArgsConstructor
@Singleton
public class SecuritiesSync {

    private final MoexSyncRepository moexSyncRepository;
    private final SecuritiesHandler securitiesService;
    private final SecuritiesSpecHandler securitiesSpecHandler;


    @Transactional
    @EventListener
    public void sync(StartupEvent startupEvent) {
        log.info("Startup sync securities");
        moexSyncRepository.findByDataTypeOrderByUpdateDateDesc(SyncTypes.Securities.value)
                .switchIfEmpty(Mono.defer(() -> {
                            fetchStocks()
                                    .doOnNext(it -> securitiesSpecHandler.fetchBySecId(it.getSecId()).subscribe())
                                    .subscribe();

                            var entity = new MoexSync();
                            entity.setDataType(SyncTypes.Securities.value);

                            log.debug("Startup sync securities FINISH");
                            moexSyncRepository.save(entity).subscribe();
                            return Mono.empty();
                        })
                )
                .filter(entity -> entity.getUpdateDate().isBefore(LocalDateTime.now().minus(Duration.ofDays(1L))))
                .publishOn(Schedulers.boundedElastic())
                .doOnNext(entity -> {
                    log.debug("Startup sync securities START");
                    fetchStocks().subscribe();
                })
                .doOnNext(entity -> {
                    entity.setDataType(SyncTypes.Securities.value);
                    moexSyncRepository.update(entity).subscribe();
                    log.debug("Startup sync securities FINISH");
                }).subscribe()
        ;
    }

    private Flux<Row> fetchStocks() {
        return securitiesService.fetch(new RequestParamSecurities(
                "1",
                "stock",
                "shares",
                0,
                "ru",
                null,
                100,
                "trade_engines"
        ));
    }

}
