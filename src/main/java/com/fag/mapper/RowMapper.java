package com.fag.mapper;

import com.fag.domain.MegaSena;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;

public class RowMapper {

    public static MegaSena rowToMegaSena(Row row, MegaSena megaSena) {
        CellMapper.cellToProperty(row.cellIterator(), megaSena);

        return megaSena;
    }

}
