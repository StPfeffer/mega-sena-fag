package com.fag.service;

import com.fag.dto.MegaSenaDTO;
import com.fag.interfaces.CellProcessorService;
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
    private final Map<Integer, CellProcessorService> columnHandlers = new HashMap<>();

    public WorkbookServiceImpl() {
        initializeColumnHandlers();
    }

    private void initializeColumnHandlers() {
        columnHandlers.put(8, this::processGanhadoresSeisDezenas);
        columnHandlers.put(10, this::processRateioSeisDezenas);
        columnHandlers.put(11, this::processGanhadoresCincoDezenas);
        columnHandlers.put(12, this::processRateioCincoDezenas);
        columnHandlers.put(13, this::processGanhadoresQuatroDezenas);
        columnHandlers.put(14, this::processRateioQuatroDezenas);
    }

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
                String columnName = sheet.getRow(0).getCell(columnIndex).getStringCellValue().toLowerCase();

                if (columnIndexes.containsKey(columnName) && cell instanceof XSSFCell) {
                    String rawValue = ((XSSFCell) cell).getRawValue();

                    if (rawValue != null) {
                        processCellValue(columnIndex, cell, row, megaSenaData);
                    }
                }
            }
        }
    }

    private void processCellValue(int columnIndex, Cell cell, Row row, MegaSenaDTO megaSenaData) {
        columnHandlers.getOrDefault(columnIndex, (c, r, d) -> {}).process(cell, row, megaSenaData);
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

    private void processGanhadoresSeisDezenas(Cell cell, Row row, MegaSenaDTO megaSenaDTO) {
        String rawValue = ((XSSFCell) cell).getRawValue();
        int valor = Integer.parseInt(rawValue);

        if (valor == 0) {
            megaSenaDTO.incrementConcursosSemSeisDezenas();
        }

        megaSenaDTO.incrementGanhadoresSeisDezenas(valor);
    }

    private void processRateioSeisDezenas(Cell cell, Row row, MegaSenaDTO megaSenaDTO) {
        String cellValue = cell.getStringCellValue().replaceAll("[^\\d,.+]", "").replaceAll("\\.", "").replaceAll(",", ".");
        BigDecimal bigValue = new BigDecimal(cellValue);

        if (megaSenaDTO.getMenorValorSeisDezenas().compareTo(BigDecimal.ZERO) == 0) {
            megaSenaDTO.setMenorValorSeisDezenas(bigValue);
        } else if (bigValue.compareTo(BigDecimal.ZERO) != 0 && bigValue.compareTo(megaSenaDTO.getMenorValorSeisDezenas()) < 0) {
            megaSenaDTO.setMenorValorSeisDezenas(bigValue);
        }

        if (bigValue.compareTo(megaSenaDTO.getMaiorValorSeisDezenas()) > 0) {
            megaSenaDTO.setMaiorValorSeisDezenas(bigValue);
        }
    }

    private void processGanhadoresCincoDezenas(Cell cell, Row row, MegaSenaDTO megaSenaDTO) {
        String rawValue = ((XSSFCell) cell).getRawValue();
        int valor = Integer.parseInt(rawValue);

        megaSenaDTO.incrementGanhadoresCincoDezenas(valor);
    }

    private void processRateioCincoDezenas(Cell cell, Row row, MegaSenaDTO megaSenaDTO) {
        String cellValue = cell.getStringCellValue().replaceAll("[^\\d,.+]", "").replaceAll("\\.", "").replaceAll(",", ".");
        BigDecimal bigValue = new BigDecimal(cellValue);

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

    private void processGanhadoresQuatroDezenas(Cell cell, Row row, MegaSenaDTO megaSenaDTO) {
        String rawValue = ((XSSFCell) cell).getRawValue();
        int valor = Integer.parseInt(rawValue);

        megaSenaDTO.incrementGanhadoresQuatroDezenas(valor);
    }

    private void processRateioQuatroDezenas(Cell cell, Row row, MegaSenaDTO megaSenaDTO) {
        String cellValue = cell.getStringCellValue().replaceAll("[^\\d,.+]", "").replaceAll("\\.", "").replaceAll(",", ".");
        BigDecimal bigValue = new BigDecimal(cellValue);

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

}