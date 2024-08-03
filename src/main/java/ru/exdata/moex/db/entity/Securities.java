package ru.exdata.moex.db.entity;

import io.micronaut.data.annotation.DateCreated;
import io.micronaut.data.annotation.DateUpdated;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.data.model.naming.NamingStrategies;
import io.micronaut.serde.annotation.Serdeable;
import lombok.*;

import java.time.LocalDateTime;

@Serdeable
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@MappedEntity(value = "securities", namingStrategy = NamingStrategies.UnderScoreSeparatedLowerCase.class)
public class Securities {

    @Id
    private Integer id;
    private String secId;
    private String shortname;
    private String regnumber;
    private String name;
    private String isin;
    private Integer isTraded;
    private Integer emitentId;
    private String emitentTitle;
    private String emitentInn;
    private String emitentOkpo;
    private String gosreg;
    private String type;
    private String groupp;
    private String primaryBoardid;
    private String marketpriceBoardid;
    @DateCreated
    private LocalDateTime createDate;
    @DateUpdated
    private LocalDateTime updateDate;

}
