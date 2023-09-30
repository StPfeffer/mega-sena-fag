package com.fag.mapper;

import com.fag.domain.MegaSena;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MegaSenaMapper {

    public static MegaSena dataToEntity(Sheet sheet) {
        MegaSena newMegaSena = new MegaSena();

        while (sheet.rowIterator().hasNext()) {
            Row row = sheet.rowIterator().next();

            if (row.getRowNum() < 1) {
                continue;
            }

            return RowMapper.rowToMegaSena(row, newMegaSena);
        }

        return null;
    }

    public static List<MegaSena> dataToEntityList(BufferedInputStream buf) {
        if (buf == null) {
            return null;
        }

        List<MegaSena> megaSenaList = new ArrayList<>();

        try {
            XSSFWorkbook workbook = new XSSFWorkbook(buf);
            XSSFSheet sheet = workbook.getSheetAt(0);

            while (sheet.rowIterator().hasNext()) {
                Row row = sheet.rowIterator().next();

                megaSenaList.add(RowMapper.rowToMegaSena(row, new MegaSena()));
            }
        } catch (IOException e) {
            return null;
        }

        return megaSenaList;
    }

}
