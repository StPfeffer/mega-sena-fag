package com.fag.service;

import com.fag.domain.MegaSena;
import com.fag.mapper.MegaSenaMapper;
import org.apache.poi.ss.usermodel.Row;

public class MegaSenaService {

    public static MegaSena createMegaSenaFromRow(Row row) {
        return MegaSenaMapper.instance().rowToEntity(row);
    }

}
