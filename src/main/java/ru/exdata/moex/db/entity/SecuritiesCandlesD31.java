package ru.exdata.moex.db.entity;

import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.data.model.naming.NamingStrategies;
import io.micronaut.serde.annotation.Serdeable;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Serdeable
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@SuperBuilder
@MappedEntity(value = "securities_candles_d31", namingStrategy = NamingStrategies.UnderScoreSeparatedLowerCase.class)
public class SecuritiesCandlesD31 extends SecuritiesCandlesAbstract {

}
