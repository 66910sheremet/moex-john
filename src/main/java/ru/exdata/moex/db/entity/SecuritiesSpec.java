package ru.exdata.moex.db.entity;

import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.data.model.naming.NamingStrategies;
import io.micronaut.serde.annotation.Serdeable;
import lombok.*;

@Serdeable
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@MappedEntity(value = "securities_spec", namingStrategy = NamingStrategies.UnderScoreSeparatedLowerCase.class)
public class SecuritiesSpec {

    @Id
    @GeneratedValue(value = GeneratedValue.Type.IDENTITY)
    private Integer id;
    private String secId;
    private String name;
    private String title;
    private String valuee;
    private String type;
    private Integer sortOrder;
    private Integer isHidden;
    private Integer precisionn;

}
