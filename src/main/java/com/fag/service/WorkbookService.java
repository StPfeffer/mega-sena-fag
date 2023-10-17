package com.fag.service;

import com.fag.domain.MegaSena;
import com.fag.interfaces.IWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.Iterator;
import java.util.LinkedList;

public class WorkbookService implements IWorkbook<XSSFWorkbook, LinkedList<MegaSena>> {

    public static WorkbookService instance() {
        return new WorkbookService();
    }

    @Override
    public LinkedList<MegaSena> resolveWorkbook(String path, int sheetIndex) {
        File file = new File(path);

        try (FileInputStream fis = new FileInputStream(file)) {
            XSSFWorkbook workbook = createWorkbook(fis);

            if (workbook == null) {
                throw new RuntimeException("Não foi possível inicializar o workbook!");
            }

            XSSFSheet sheet = workbook.getSheetAt(sheetIndex);

            Iterator<Row> rows = sheet.rowIterator();

            LinkedList<MegaSena> megaSenaList = new LinkedList<>();

            while (rows.hasNext()) {
                Row row = rows.next();

                if (row.getRowNum() < 1) {
                    continue;
                }

                MegaSena newMegaSena = MegaSenaService.createMegaSenaFromRow(row);
                megaSenaList.add(newMegaSena);
            }

            return megaSenaList;
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    public XSSFWorkbook createWorkbook(FileInputStream fis) {
        try {
            return new XSSFWorkbook(fis);
        } catch (IOException e) {
            return null;
        }
    }

}
