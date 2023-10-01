package com.fag;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Iterator;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        String path = "./src/main/java/com/fag/data/Mega-Sena.xlsx";
        File file = new File(path);

        FileInputStream fis = new FileInputStream(file);

        int qtdeConcursosSemSeisDezenas = 0;
        BigDecimal menorValorQuatroDezenas = BigDecimal.ZERO;
        BigDecimal menorValorCincoDezenas = BigDecimal.ZERO;
        BigDecimal menorValorSeisDezenas = BigDecimal.ZERO;
        BigDecimal maiorValorQuatroDezenas = BigDecimal.ZERO;
        BigDecimal maiorValorCincoDezenas = BigDecimal.ZERO;
        BigDecimal maiorValorSeisDezenas = BigDecimal.ZERO;
        int qtdeGanhadoresQuatroDezenasTodosConcursos = 0;
        int qtdeGanhadoresCincoDezenasTodosConcursos = 0;
        int qtdeGanhadoresSeisDezenasTodosConcursos = 0;

        try {
            XSSFWorkbook workbook = new XSSFWorkbook(fis);
            XSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rows = sheet.rowIterator();

            while (rows.hasNext()) {
                Row row = rows.next();

                if (row.getRowNum() < 1) {
                    continue;
                }

                Iterator<Cell> cells = row.cellIterator();

                while (cells.hasNext()) {
                    Cell cell = cells.next();

                    int valor;
                    BigDecimal bigValue;

                    switch (cell.getColumnIndex()) {
                        case 0: // concurso
                            continue;
                        case 1: // data
                            continue;
                        case 2: // bola 1
                            continue;
                        case 3: // bola 2
                            continue;
                        case 4: // bola 3
                            continue;
                        case 5: // bola 4
                            continue;
                        case 6: // bola 5
                            continue;
                        case 7: // bola 6
                            continue;
                        case 8: // ganhadores 6 acertos
                            valor = Integer.parseInt(((XSSFCell) cell).getRawValue());

                            if (valor == 0) {
                                qtdeConcursosSemSeisDezenas++;
                            }

                            qtdeGanhadoresSeisDezenasTodosConcursos += valor;

                            continue;
                        case 9: // uf
                            continue;
                        case 10: // rateio 6 acertos
                            bigValue = new BigDecimal(cell.getStringCellValue().substring(2).replaceAll("\\.", "").replaceAll(",", "."));

                            if (row.getRowNum() == 1) {
                                // se o valor for 0, é porque não houve ganhador nas 6 dezenas, então ignoro
                                if (bigValue.compareTo(BigDecimal.ZERO) > 0) {
                                    menorValorSeisDezenas = bigValue;
                                }

                                maiorValorSeisDezenas = bigValue;
                            } else {
                                if (bigValue.compareTo(menorValorSeisDezenas) < 0 && bigValue.compareTo(BigDecimal.ZERO) > 0) {
                                    menorValorSeisDezenas = bigValue;
                                }

                                if (bigValue.compareTo(maiorValorSeisDezenas) > 0) {
                                    maiorValorSeisDezenas = bigValue;
                                }
                            }

                            continue;
                        case 11: // ganhadores 5 acertos
                            valor = Integer.parseInt(((XSSFCell) cell).getRawValue());
                            qtdeGanhadoresCincoDezenasTodosConcursos += valor;

                            continue;
                        case 12: // rateio 5 acertos
                            bigValue = new BigDecimal(cell.getStringCellValue().substring(2).replaceAll("\\.", "").replaceAll(",", "."));

                            if (row.getRowNum() == 1) {
                                menorValorCincoDezenas = bigValue;
                                maiorValorCincoDezenas = bigValue;
                            } else {
                                if (bigValue.compareTo(menorValorCincoDezenas) < 0) {
                                    menorValorCincoDezenas = bigValue;
                                }

                                if (bigValue.compareTo(maiorValorCincoDezenas) > 0) {
                                    maiorValorCincoDezenas = bigValue;
                                }
                            }

                            continue;
                        case 13: // ganhadores 4 acertos
                            valor = Integer.parseInt(((XSSFCell) cell).getRawValue());
                            qtdeGanhadoresQuatroDezenasTodosConcursos += valor;

                            continue;
                        case 14: // rateio 4 acertos
                            bigValue = new BigDecimal(cell.getStringCellValue().substring(2).replaceAll("\\.", "").replaceAll(",", "."));

                            if (row.getRowNum() == 1) {
                                menorValorQuatroDezenas = bigValue;
                                maiorValorQuatroDezenas = bigValue;
                            } else {
                                if (bigValue.compareTo(menorValorQuatroDezenas) < 0) {
                                    menorValorQuatroDezenas = bigValue;
                                }

                                if (bigValue.compareTo(maiorValorQuatroDezenas) > 0) {
                                    maiorValorQuatroDezenas = bigValue;
                                }
                            }

                            continue;
                        case 15: // acumulado 6 acertos
                            continue;
                        case 16: // arrecadacao total
                            continue;
                        case 17: // estimativa premio
                            continue;
                        case 18: // acumulado mega virada
                            continue;
                        case 19: // observacoes
                            continue;
                        default:
                            throw new UnsupportedOperationException("Não existe mapeamento para essa coluna");
                    }
                }
            }

            System.out.println("Quantidade de concursos sem seis dezenas: " + qtdeConcursosSemSeisDezenas);
            System.out.println("Menor valor quatro dezenas: " + menorValorQuatroDezenas);
            System.out.println("Menor valor cinco dezenas: " + menorValorCincoDezenas);
            System.out.println("Menor valor seis dezenas: " + menorValorSeisDezenas);
            System.out.println("Maior valor quatro dezenas: " + maiorValorQuatroDezenas);
            System.out.println("Maior valor cinco dezenas: " + maiorValorCincoDezenas);
            System.out.println("Maior valor seis dezenas: " + maiorValorSeisDezenas);
            System.out.println("Quantidade de ganhadores de quatro dezenas em todos os concursos: " + qtdeGanhadoresQuatroDezenasTodosConcursos);
            System.out.println("Quantidade de ganhadores de cinco dezenas em todos os concursos: " + qtdeGanhadoresCincoDezenasTodosConcursos);
            System.out.println("Quantidade de ganhadores de seis dezenas em todos os concursos: " + qtdeGanhadoresSeisDezenasTodosConcursos);

        } catch (IOException ignored) {
        }
    }

}