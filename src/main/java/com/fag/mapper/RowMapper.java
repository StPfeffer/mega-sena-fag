package com.fag.mapper;

import com.fag.domain.MegaSena;
import org.apache.poi.ss.usermodel.Row;

public class RowMapper {

    public static MegaSena rowToMegaSena(Row row, MegaSena megaSena) {
        CellMapper.cellToProperty(row.cellIterator(), megaSena);

        return megaSena;
    }

}
