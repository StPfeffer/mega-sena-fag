package com.fag.service;

import com.fag.dto.MegaSenaDTO;
import com.fag.interfaces.IWorkbookService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class WorkbookServiceImpl implements IWorkbookService {

    @Override
    public MegaSenaDTO processExcelFile(FileInputStream fis, Map<String, Integer> columnIndexes, int sheetIndex) throws IOException {
        MegaSenaDTO megaSenaData = new MegaSenaDTO();

        try {
            XSSFWorkbook workbook = new XSSFWorkbook(fis);
            XSSFSheet sheet = workbook.getSheetAt(sheetIndex);

            processRows(sheet, columnIndexes, megaSenaData);

        } catch (IOException | NumberFormatException e) {
            throw new RuntimeException(e);
        }

        return megaSenaData;
    }

    private void processRows(XSSFSheet sheet, Map<String, Integer> columnIndexes, MegaSenaDTO megaSenaData) {
        Iterator<Row> rows = sheet.rowIterator();

        while (rows.hasNext()) {
            Row row = rows.next();

            if (row.getRowNum() < 1) {
                continue;
            }

            Iterator<Cell> cells = row.cellIterator();

            while (cells.hasNext()) {
                Cell cell = cells.next();
                int columnIndex = cell.getColumnIndex();
                String columnName = sheet.getRow(0).getCell(columnIndex).getStringCellValue();

                if (columnIndexes.containsKey(columnName)) {
                    processCellValue(columnIndex, cell, row, megaSenaData);
                }
            }
        }
    }

    private void processCellValue(int columnIndex, Cell cell, Row row, MegaSenaDTO megaSenaData) {
        int valor;
        BigDecimal bigValue;

        switch (columnIndex) {
            case 8 -> { // ganhadores 6 acertos
                valor = Integer.parseInt(((XSSFCell) cell).getRawValue());
                processGanhadoresSeisDezenas(valor, megaSenaData);
            }
            case 10 -> { // rateio 6 acertos
                bigValue = new BigDecimal(cell.getStringCellValue().substring(2).replaceAll("\\.", "").replaceAll(",", "."));
                processRateioSeisDezenas(bigValue, row, megaSenaData);
            }
            case 11 -> { // ganhadores 5 acertos
                valor = Integer.parseInt(((XSSFCell) cell).getRawValue());
                processGanhadoresCincoDezenas(valor, megaSenaData);
            }
            case 12 -> { // rateio 5 acertos
                bigValue = new BigDecimal(cell.getStringCellValue().substring(2).replaceAll("\\.", "").replaceAll(",", "."));
                processRateioCincoDezenas(bigValue, row, megaSenaData);
            }
            case 13 -> { // ganhadores 4 acertos
                valor = Integer.parseInt(((XSSFCell) cell).getRawValue());
                processGanhadoresQuatroDezenas(valor, megaSenaData);
            }
            case 14 -> { // rateio 4 acertos
                bigValue = new BigDecimal(cell.getStringCellValue().substring(2).replaceAll("\\.", "").replaceAll(",", "."));
                processRateioQuatroDezenas(bigValue, row, megaSenaData);
            }
        }
    }

    private void processGanhadoresSeisDezenas(int valor, MegaSenaDTO megaSenaDTO) {
        if (valor == 0) {
            megaSenaDTO.incrementConcursosSemSeisDezenas();
        }

        megaSenaDTO.incrementGanhadoresSeisDezenas(valor);
    }

    private void processRateioSeisDezenas(BigDecimal bigValue, Row row, MegaSenaDTO megaSenaDTO) {

        if (row.getRowNum() == 1) {
            if (bigValue.compareTo(BigDecimal.ZERO) > 0) {
                megaSenaDTO.setMenorValorSeisDezenas(bigValue);
            }

            megaSenaDTO.setMaiorValorSeisDezenas(bigValue);
        } else {
            if (bigValue.compareTo(megaSenaDTO.getMenorValorSeisDezenas()) < 0 && bigValue.compareTo(BigDecimal.ZERO) > 0) {
                megaSenaDTO.setMenorValorSeisDezenas(bigValue);
            }

            if (bigValue.compareTo(megaSenaDTO.getMaiorValorSeisDezenas()) > 0) {
                megaSenaDTO.setMaiorValorSeisDezenas(bigValue);
            }
        }
    }

    private void processGanhadoresCincoDezenas(int valor, MegaSenaDTO megaSenaDTO) {
        megaSenaDTO.incrementGanhadoresCincoDezenas(valor);
    }

    private void processRateioCincoDezenas(BigDecimal bigValue, Row row, MegaSenaDTO megaSenaDTO) {
        if (row.getRowNum() == 1) {
            megaSenaDTO.setMenorValorCincoDezenas(bigValue);
            megaSenaDTO.setMaiorValorCincoDezenas(bigValue);
        } else {
            if (bigValue.compareTo(megaSenaDTO.getMenorValorCincoDezenas()) < 0) {
                megaSenaDTO.setMenorValorCincoDezenas(bigValue);
            }

            if (bigValue.compareTo(megaSenaDTO.getMaiorValorCincoDezenas()) > 0) {
                megaSenaDTO.setMaiorValorCincoDezenas(bigValue);
            }
        }
    }

    private void processGanhadoresQuatroDezenas(int valor, MegaSenaDTO megaSenaDTO) {
        megaSenaDTO.incrementGanhadoresQuatroDezenas(valor);
    }

    private void processRateioQuatroDezenas(BigDecimal bigValue, Row row, MegaSenaDTO megaSenaDTO) {
        if (row.getRowNum() == 1) {
            megaSenaDTO.setMenorValorQuatroDezenas(bigValue);
            megaSenaDTO.setMaiorValorQuatroDezenas(bigValue);
        } else {
            if (bigValue.compareTo(megaSenaDTO.getMenorValorQuatroDezenas()) < 0) {
                megaSenaDTO.setMenorValorQuatroDezenas(bigValue);
            }

            if (bigValue.compareTo(megaSenaDTO.getMaiorValorQuatroDezenas()) > 0) {
                megaSenaDTO.setMaiorValorQuatroDezenas(bigValue);
            }
        }
    }

    @Override
    public Map<String, Integer> initializeColumnIndexes(FileInputStream fis) throws IOException {
        Map<String, Integer> columnIndexes = new HashMap<>();
        columnIndexes.put("ganhadores 6 acertos", 8);
        columnIndexes.put("rateio 6 acertos", 10);
        columnIndexes.put("ganhadores 5 acertos", 11);
        columnIndexes.put("rateio 5 acertos", 12);
        columnIndexes.put("ganhadores 4 acertos", 13);
        columnIndexes.put("rateio 4 acertos", 14);

        return columnIndexes;
    }

}
