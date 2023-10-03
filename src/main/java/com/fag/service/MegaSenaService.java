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
        MegaSenaDTO megaSenaData = new MegaSenaDTO();
        Map<String, Integer> columnIndexes = initializeColumnIndexes();

        try {
            XSSFWorkbook workbook = new XSSFWorkbook(fis);
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

                    if (columnIndexes.containsKey(columnName)) {
                        int valor;
                        BigDecimal bigValue;

                        switch (columnIndex) {
                            case 8 -> { // ganhadores 6 acertos
                                valor = Integer.parseInt(((XSSFCell) cell).getRawValue());
                                if (valor == 0) {
                                    megaSenaData.incrementConcursosSemSeisDezenas();
                                }
                                megaSenaData.incrementGanhadoresSeisDezenas(valor);
                            }
                            case 10 -> { // rateio 6 acertos
                                bigValue = new BigDecimal(cell.getStringCellValue().substring(2).replaceAll("\\.", "").replaceAll(",", "."));
                                if (row.getRowNum() == 1) {
                                    if (bigValue.compareTo(BigDecimal.ZERO) > 0) {
                                        megaSenaData.setMenorValorSeisDezenas(bigValue);
                                    }

                                    megaSenaData.setMaiorValorSeisDezenas(bigValue);
                                } else {
                                    if (bigValue.compareTo(megaSenaData.getMenorValorSeisDezenas()) < 0 && bigValue.compareTo(BigDecimal.ZERO) > 0) {
                                        megaSenaData.setMenorValorSeisDezenas(bigValue);
                                    }

                                    if (bigValue.compareTo(megaSenaData.getMaiorValorSeisDezenas()) > 0) {
                                        megaSenaData.setMaiorValorSeisDezenas(bigValue);
                                    }
                                }
                            }
                            case 11 -> { // ganhadores 5 acertos
                                valor = Integer.parseInt(((XSSFCell) cell).getRawValue());
                                megaSenaData.incrementGanhadoresCincoDezenas(valor);
                            }
                            case 12 -> { // rateio 5 acertos
                                bigValue = new BigDecimal(cell.getStringCellValue().substring(2).replaceAll("\\.", "").replaceAll(",", "."));
                                if (row.getRowNum() == 1) {
                                    megaSenaData.setMenorValorCincoDezenas(bigValue);
                                    megaSenaData.setMaiorValorCincoDezenas(bigValue);
                                } else {
                                    if (bigValue.compareTo(megaSenaData.getMenorValorCincoDezenas()) < 0) {
                                        megaSenaData.setMenorValorCincoDezenas(bigValue);
                                    }

                                    if (bigValue.compareTo(megaSenaData.getMaiorValorCincoDezenas()) > 0) {
                                        megaSenaData.setMaiorValorCincoDezenas(bigValue);
                                    }
                                }
                            }
                            case 13 -> { // ganhadores 4 acertos
                                valor = Integer.parseInt(((XSSFCell) cell).getRawValue());
                                megaSenaData.incrementGanhadoresQuatroDezenas(valor);
                            }
                            case 14 -> { // rateio 4 acertos
                                bigValue = new BigDecimal(cell.getStringCellValue().substring(2).replaceAll("\\.", "").replaceAll(",", "."));
                                if (row.getRowNum() == 1) {
                                    megaSenaData.setMenorValorQuatroDezenas(bigValue);
                                    megaSenaData.setMaiorValorQuatroDezenas(bigValue);
                                } else {
                                    if (bigValue.compareTo(megaSenaData.getMenorValorQuatroDezenas()) < 0) {
                                        megaSenaData.setMenorValorQuatroDezenas(bigValue);
                                    }

                                    if (bigValue.compareTo(megaSenaData.getMaiorValorQuatroDezenas()) > 0) {
                                        megaSenaData.setMaiorValorQuatroDezenas(bigValue);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (IOException | NumberFormatException e) {
            throw new RuntimeException(e);
        }

        return megaSenaData;
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

    private static void printResults(MegaSenaDTO megaSenaDTO) {
        System.out.println("Quantidade de concursos sem seis dezenas: " + megaSenaDTO.getConcursosSemSeisDezenas());
        System.out.println("Menor valor quatro dezenas: " + megaSenaDTO.getMenorValorQuatroDezenas());
        System.out.println("Menor valor cinco dezenas: " + megaSenaDTO.getMenorValorCincoDezenas());
        System.out.println("Menor valor seis dezenas: " + megaSenaDTO.getMenorValorSeisDezenas());
        System.out.println("Maior valor quatro dezenas: " + megaSenaDTO.getMaiorValorQuatroDezenas());
        System.out.println("Maior valor cinco dezenas: " + megaSenaDTO.getMaiorValorCincoDezenas());
        System.out.println("Maior valor seis dezenas: " + megaSenaDTO.getMaiorValorSeisDezenas());
        System.out.println("Quantidade de ganhadores de quatro dezenas em todos os concursos: " + megaSenaDTO.getGanhadoresQuatroDezenasTodosConcursos());
        System.out.println("Quantidade de ganhadores de cinco dezenas em todos os concursos: " + megaSenaDTO.getGanhadoresCincoDezenasTodosConcursos());
        System.out.println("Quantidade de ganhadores de seis dezenas em todos os concursos: " + megaSenaDTO.getGanhadoresSeisDezenasTodosConcursos());
    }

}
