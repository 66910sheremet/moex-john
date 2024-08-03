package ru.exdata.moex.db.dao;

import io.micronaut.transaction.annotation.Transactional;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;
import ru.exdata.moex.db.entity.Securities;
import ru.exdata.moex.db.repository.SecuritiesRepository;
import ru.exdata.moex.dto.securities.Row;
import ru.exdata.moex.mapper.SecuritiesMapper;

@RequiredArgsConstructor
@Singleton
public class SecuritiesDao {

    private final SecuritiesRepository securitiesRepository;

    @Transactional
    public Mono<Securities> save(Row dto) {
        if (dto == null || dto.getId() == null) {
            return Mono.just(new Securities());
        }
        var sec = SecuritiesMapper.fromDtoToEntity(dto);
        return securitiesRepository
                .findById(dto.getId())
                .doOnNext(it -> securitiesRepository.update(sec).subscribe())
                .switchIfEmpty(securitiesRepository.save(sec));
    }

}
