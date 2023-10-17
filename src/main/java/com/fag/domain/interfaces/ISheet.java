package com.fag.domain.interfaces;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.Iterator;

public interface ISheet<SHEET extends Sheet> {

    SHEET getSheetFromWorkbook(Workbook workbook, int sheetIndex);

    Iterator<Row> sheetToRowIterator(SHEET sheet);

}
