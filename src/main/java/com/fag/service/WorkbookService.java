package com.fag.service;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class WorkbookService {

    public static void iterateThroughWorkbook(FileInputStream fis, int sheetIndex) {
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(fis);
            XSSFSheet sheet = workbook.getSheetAt(sheetIndex);

            RowService.iterateThroughRow(sheet.rowIterator());
        } catch (IOException ignored) {
        }
    }

}
