package ru.exdata.moex.mapper;

import ru.exdata.moex.db.entity.SecuritiesSpec;
import ru.exdata.moex.dto.securitiesSpec.Row;

public class SecuritiesSpecMapper {

    public static SecuritiesSpec fromDtoToEntity(Row it, String secId) {
        var sec = new SecuritiesSpec();
        sec.setSecId(secId);
        sec.setName(it.getName());
        sec.setTitle(it.getTitle());
        sec.setValuee(it.getValuee());
        sec.setType(it.getType());
        sec.setSortOrder(it.getSortOrder());
        sec.setIsHidden(it.getIsHidden());
        sec.setPrecisionn(it.getPrecisionn());
        return sec;
    }

    public static Row fromEntityToDto(SecuritiesSpec it) {
        var sec = new Row();
        sec.setName(it.getName());
        sec.setTitle(it.getTitle());
        sec.setValuee(it.getValuee());
        sec.setType(it.getType());
        sec.setSortOrder(it.getSortOrder());
        sec.setIsHidden(it.getIsHidden());
        sec.setPrecisionn(it.getPrecisionn());
        return sec;
    }

}
