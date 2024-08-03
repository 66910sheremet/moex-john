package ru.exdata.moex.dto.securities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import io.micronaut.serde.annotation.Serdeable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Serdeable
@Data
@JsonIgnoreProperties(ignoreUnknown = false)
public class Row {

    @JacksonXmlProperty(isAttribute = true, localName = "id")
    private Integer id;
    @JacksonXmlProperty(isAttribute = true, localName = "secid")
    private String secId;
    @JacksonXmlProperty(isAttribute = true, localName = "shortname")
    private String shortname;
    @JacksonXmlProperty(isAttribute = true, localName = "regnumber")
    private String regnumber;
    @JacksonXmlProperty(isAttribute = true, localName = "name")
    private String name;
    @JacksonXmlProperty(isAttribute = true, localName = "isin")
    private String isin;
    @JacksonXmlProperty(isAttribute = true, localName = "is_traded")
    private Integer isTraded;
    @JacksonXmlProperty(isAttribute = true, localName = "emitent_id")
    private Integer emitentId;
    @JacksonXmlProperty(isAttribute = true, localName = "emitent_title")
    private String emitentTitle;
    @JacksonXmlProperty(isAttribute = true, localName = "emitent_inn")
    private String emitentInn;
    @JacksonXmlProperty(isAttribute = true, localName = "emitent_okpo")
    private String emitentOkpo;
    @JacksonXmlProperty(isAttribute = true, localName = "gosreg")
    private String gosreg;
    @JacksonXmlProperty(isAttribute = true, localName = "type")
    private String type;
    @JacksonXmlProperty(isAttribute = true, localName = "groupp")
    private String groupp;
    @JacksonXmlProperty(isAttribute = true, localName = "primary_boardid")
    private String primaryBoardid;
    @JacksonXmlProperty(isAttribute = true, localName = "marketprice_boardid")
    private String marketpriceBoardid;

}
