package ru.exdata.moex.db.repository;


import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.async.annotation.SingleResult;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.r2dbc.annotation.R2dbcRepository;
import io.micronaut.data.repository.reactive.ReactiveStreamsCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.exdata.moex.db.entity.SecuritiesSpec;

@R2dbcRepository(dialect = Dialect.POSTGRES)
public interface SecuritiesSpecRepository extends ReactiveStreamsCrudRepository<SecuritiesSpec, Integer> {

    @NonNull Flux<SecuritiesSpec> findBySecId(@NonNull String secId);

    @Override
    @SingleResult
    @NonNull <S extends SecuritiesSpec> Mono<S> save(@NonNull S entity);

    @Override
    @NonNull <S extends SecuritiesSpec> Mono<S> update(@NonNull S entity);

}
