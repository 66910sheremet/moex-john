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
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@SuperBuilder
@MappedEntity(value = "securities_candles_m10", namingStrategy = NamingStrategies.UnderScoreSeparatedLowerCase.class)
public class SecuritiesCandlesM10 extends SecuritiesCandlesAbstract {

}
