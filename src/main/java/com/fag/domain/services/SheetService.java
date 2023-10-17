package com.fag.domain.services;

import com.fag.domain.interfaces.ISheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import java.util.Iterator;

public class SheetService implements ISheet<XSSFSheet> {

    public static SheetService instance() {
        return new SheetService();
    }

    @Override
    public XSSFSheet getSheetFromWorkbook(Workbook workbook, int sheetIndex) {
        if (workbook == null) {
            return null;
        }

        return (XSSFSheet) workbook.getSheetAt(sheetIndex);
    }

    @Override
    public Iterator<Row> sheetToRowIterator(XSSFSheet sheet) {
        return sheet.rowIterator();
    }


}
