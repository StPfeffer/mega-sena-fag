package com.fag.service;

import com.fag.domain.MegaSena;
import com.fag.interfaces.IWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.Iterator;
import java.util.LinkedList;

public class WorkbookService implements IWorkbook<XSSFWorkbook> {

    public static WorkbookService instance() {
        return new WorkbookService();
    }

    @Override
    public void resolveWorkbook(String path, int sheetIndex) throws FileNotFoundException {
        File file = new File(path);
        FileInputStream fis = new FileInputStream(file);

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

        if (megaSenaList.size() == 3) {
            System.out.println("aqui");
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
