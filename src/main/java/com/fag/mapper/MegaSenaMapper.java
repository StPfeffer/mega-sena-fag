package com.fag.mapper;

import com.fag.domain.MegaSena;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MegaSenaMapper {

    public static List<MegaSena> dataToEntityList(FileInputStream fis, int sheetIndex) {
        if (fis == null) {
            return null;
        }

        List<MegaSena> megaSenaList = new ArrayList<>();

        try {
            XSSFWorkbook workbook = new XSSFWorkbook(fis);
            XSSFSheet sheet = workbook.getSheetAt(sheetIndex);

            while (sheet.rowIterator().hasNext()) {
                Row row = sheet.rowIterator().next();

                megaSenaList.add(dataToEntity(row));
            }
        } catch (IOException e) {
            return null;
        }

        return megaSenaList;
    }

    public static MegaSena dataToEntity(Row row) {
        return RowMapper.rowToMegaSena(row, new MegaSena());
    }

}
