package com.fag.interfaces;

import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public interface IWorkbook<T extends Workbook> {

    void resolveWorkbook(String path, int sheetIndex) throws FileNotFoundException;

    T createWorkbook(FileInputStream fis);

}
