package com.fag.service;

import com.fag.dto.MegaSenaDTO;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MegaSenaService {

    public static void analyzer(String path) {
        File file = new File(path);

        try (FileInputStream fis = new FileInputStream(file)) {
            MegaSenaDTO dto = processExcelFile(fis, 0);

            printResults(dto);
        } catch (IOException ignored) {
        }
    }

    private static MegaSenaDTO processExcelFile(FileInputStream fis, int sheetIndex) throws IOException {
        MegaSenaDTO megaSenaDTO = new MegaSenaDTO();
        Map<String, Integer> columns = initializeColumns();

        try (XSSFWorkbook workbook = new XSSFWorkbook(fis)) {
            XSSFSheet sheet = workbook.getSheetAt(sheetIndex);
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

                    if (columns.containsKey(columnName)) {
                        int valor;
                        BigDecimal bigValue;

                        switch (columnIndex) {
                            case 8: // ganhadores 6 acertos
                                valor = Integer.parseInt(((XSSFCell) cell).getRawValue());

                                if (valor == 0) {
                                    megaSenaDTO.incrementConcursosSemSeisDezenas();
                                }

                                megaSenaDTO.incrementGanhadoresSeisDezenas(valor);
                                break;
                            case 10: // rateio 6 acertos
                                bigValue = new BigDecimal(cell.getStringCellValue().substring(2).replaceAll("\\.", "").replaceAll(",", "."));

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
                                break;
                            case 11: // ganhadores 5 acertos
                                valor = Integer.parseInt(((XSSFCell) cell).getRawValue());
                                megaSenaDTO.incrementGanhadoresCincoDezenas(valor);
                                break;
                            case 12: // rateio 5 acertos
                                bigValue = new BigDecimal(cell.getStringCellValue().substring(2).replaceAll("\\.", "").replaceAll(",", "."));

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
                                break;
                            case 13: // ganhadores 4 acertos
                                valor = Integer.parseInt(((XSSFCell) cell).getRawValue());
                                megaSenaDTO.incrementGanhadoresQuatroDezenas(valor);
                                break;
                            case 14: // rateio 4 acertos
                                bigValue = new BigDecimal(cell.getStringCellValue().substring(2).replaceAll("\\.", "").replaceAll(",", "."));

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
                                break;
                        }
                    }
                }
            }
        }

        return megaSenaDTO;
    }

    private static Map<String, Integer> initializeColumnIndexes() {
        Map<String, Integer> columnIndexes = new HashMap<>();
        columnIndexes.put("ganhadores 6 acertos", 8);
        columnIndexes.put("rateio 6 acertos", 10);
        columnIndexes.put("ganhadores 5 acertos", 11);
        columnIndexes.put("rateio 5 acertos", 12);
        columnIndexes.put("ganhadores 4 acertos", 13);
        columnIndexes.put("rateio 4 acertos", 14);
        return columnIndexes;
    }

    private static void printResults(MegaSenaDTO dto) {
        System.out.println("Quantidade de concursos sem seis dezenas: " + dto.getConcursosSemSeisDezenas());
        System.out.println("Menor valor quatro dezenas: " + dto.getMenorValorQuatroDezenas());
        System.out.println("Menor valor cinco dezenas: " + dto.getMenorValorCincoDezenas());
        System.out.println("Menor valor seis dezenas: " + dto.getMenorValorSeisDezenas());
        System.out.println("Maior valor quatro dezenas: " + dto.getMaiorValorQuatroDezenas());
        System.out.println("Maior valor cinco dezenas: " + dto.getMaiorValorCincoDezenas());
        System.out.println("Maior valor seis dezenas: " + dto.getMaiorValorSeisDezenas());
        System.out.println("Quantidade de ganhadores de quatro dezenas em todos os concursos: " + dto.getGanhadoresQuatroDezenasTodosConcursos());
        System.out.println("Quantidade de ganhadores de cinco dezenas em todos os concursos: " + dto.getGanhadoresCincoDezenasTodosConcursos());
        System.out.println("Quantidade de ganhadores de seis dezenas em todos os concursos: " + dto.getGanhadoresSeisDezenasTodosConcursos());
    }

}
