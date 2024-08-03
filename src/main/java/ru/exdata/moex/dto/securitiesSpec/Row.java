package ru.exdata.moex.dto.securitiesSpec;

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

    @JacksonXmlProperty(isAttribute = true, localName = "name")
    private String name;
    @JacksonXmlProperty(isAttribute = true, localName = "title")
    private String title;
    @JacksonXmlProperty(isAttribute = true, localName = "value")
    private String valuee;
    @JacksonXmlProperty(isAttribute = true, localName = "type")
    private String type;
    @JacksonXmlProperty(isAttribute = true, localName = "sort_order")
    private Integer sortOrder;
    @JacksonXmlProperty(isAttribute = true, localName = "is_hidden")
    private Integer isHidden;
    @JacksonXmlProperty(isAttribute = true, localName = "precisionn")
    private Integer precisionn;

}
