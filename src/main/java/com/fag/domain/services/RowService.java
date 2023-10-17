package com.fag.domain.services;

import com.fag.domain.interfaces.IRow;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFRow;

import java.util.Iterator;

public class RowService implements IRow<XSSFRow> {

    public static RowService instance() {
        return new RowService();
    }

    @Override
    public Iterator<Row> getRowIteratorFromSheet(Sheet sheet) {
        return sheet.rowIterator();
    }

    @Override
    public Iterator<Cell> rowToCellIterator(XSSFRow row) {
        return row.cellIterator();
    }

}
