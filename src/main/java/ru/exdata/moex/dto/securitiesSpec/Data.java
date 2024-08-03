package ru.exdata.moex.dto.securitiesSpec;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Getter;

@Getter
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "id")
@JsonSubTypes({
        @JsonSubTypes.Type(name = "boards", value = DataBoards.class),
        @JsonSubTypes.Type(name = "description", value = DataDescription.class)
})
public abstract class Data {

    @JacksonXmlProperty(isAttribute = true)
    private String id;

}
