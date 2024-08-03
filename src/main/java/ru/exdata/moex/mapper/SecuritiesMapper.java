package ru.exdata.moex.mapper;

import ru.exdata.moex.db.entity.Securities;
import ru.exdata.moex.dto.securities.Row;

public class SecuritiesMapper {

    public static Securities fromDtoToEntity(Row it) {
        var sec = new Securities();
        sec.setId(it.getId());
        sec.setSecId(it.getSecId());
        sec.setShortname(it.getShortname());
        sec.setRegnumber(it.getRegnumber());
        sec.setName(it.getName());
        sec.setIsin(it.getIsin());
        sec.setIsTraded(it.getIsTraded());
        sec.setEmitentId(it.getEmitentId());
        sec.setEmitentTitle(it.getEmitentTitle());
        sec.setEmitentInn(it.getEmitentInn());
        sec.setEmitentOkpo(it.getEmitentOkpo());
        sec.setGosreg(it.getGosreg());
        sec.setType(it.getType());
        sec.setGroupp(it.getGroupp());
        sec.setPrimaryBoardid(it.getPrimaryBoardid());
        sec.setMarketpriceBoardid(it.getMarketpriceBoardid());
        return sec;
    }

}
