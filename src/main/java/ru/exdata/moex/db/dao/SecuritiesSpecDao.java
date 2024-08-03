package ru.exdata.moex.db.dao;

import io.micronaut.transaction.annotation.Transactional;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import ru.exdata.moex.db.entity.SecuritiesSpec;
import ru.exdata.moex.db.repository.SecuritiesSpecRepository;
import ru.exdata.moex.dto.securitiesSpec.Row;
import ru.exdata.moex.mapper.SecuritiesSpecMapper;

@Slf4j
@RequiredArgsConstructor
@Singleton
public class SecuritiesSpecDao {

    private final SecuritiesSpecRepository securitiesSpecRepository;

    @Transactional
    public Flux<SecuritiesSpec> findBySecId(String secId) {
        return securitiesSpecRepository.findBySecId(secId);
    }

    @Transactional
    public Flux<SecuritiesSpec> save(Row dto, String secId) {
        log.info("save security spec for, {}", secId);
        if (dto == null || dto.getName() == null || dto.getValuee() == null || secId == null) {
            return Flux.empty();
        }
        var sec = SecuritiesSpecMapper.fromDtoToEntity(dto, secId);
        return securitiesSpecRepository
                .findBySecId(sec.getSecId())
                .doOnNext(it -> securitiesSpecRepository.update(sec).subscribe())
                .switchIfEmpty(securitiesSpecRepository.save(sec));
    }

}
