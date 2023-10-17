package com.fag.domain.interfaces;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileInputStream;

public interface IWorkbook<WORKBOOK extends Workbook, ENTITY> {

    WORKBOOK resolveWorkbook(String path);

    WORKBOOK createWorkbook(FileInputStream fis);

    Sheet workbookToSheet(WORKBOOK workbook, int sheetIndex);

    ENTITY resolveWorkbookToMegaSenaList(String path, int sheetIndex);

}
