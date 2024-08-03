package ru.exdata.moex.dto.securities;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import io.micronaut.serde.annotation.Serdeable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Serdeable
@lombok.Data
public class Data {

    /*@JacksonXmlProperty(isAttribute = false, localName = "metadata")
    private Metadata metadata;*/

    @JacksonXmlElementWrapper(localName = "rows", useWrapping = true)
    @JacksonXmlProperty(/*isAttribute = false, */localName = "row")
    private List<Row> rows;

}
