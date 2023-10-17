package com.fag.domain.services;

import com.fag.domain.entities.MegaSena;
import com.fag.domain.mappers.MegaSenaMapper;
import org.apache.poi.ss.usermodel.Row;

public class MegaSenaService {

    public static MegaSena createMegaSenaFromRow(Row row) {
        return MegaSenaMapper.instance().rowToEntity(row);
    }

}
