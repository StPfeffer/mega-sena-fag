package com.fag;

import com.fag.service.MegaSenaService;
import com.fag.service.RowService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        String path = "./src/main/java/com/fag/data/Mega-Sena.xlsx";
        File file = new File(path);

        FileInputStream fis = new FileInputStream(file);

        int qtdeConcursosSemSeisDezenas = 0;
        int menorValorQuatroDezenas = 0;
        int menorValorCincoDezenas = 0;
        int menorValorSeisDezenas = 0;
        int maiorValorQuatroDezenas = 0;
        int maiorValorCincoDezenas = 0;
        int maiorValorSeisDezenas = 0;
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

                    switch (cell.getColumnIndex()) {
                        case 0: // concurso
                            continue;
                        case 1: // data
                            continue;
                        case 2: // bola 1
                            break;
                        case 3: // bola 2
                            break;
                        case 4: // bola 3
                            break;
                        case 5: // bola 4
                            break;
                        case 6: // bola 5
                            break;
                        case 7: // bola 6
                            break;
                        case 8: // ganhadores 6 acertos
                            valor = Integer.parseInt(((XSSFCell) cell).getRawValue());

                            if (valor == 0) {
                                qtdeConcursosSemSeisDezenas++;
                            }

                            qtdeGanhadoresSeisDezenasTodosConcursos += valor;

                            break;
                        case 9: // uf
                            continue;
                        case 10: // rateio 6 acertos
                            valor = Integer.parseInt(((XSSFCell) cell).getRawValue());

                            if (row.getRowNum() == 1) {
                                menorValorSeisDezenas = valor;
                            } else {
                                if (valor < menorValorSeisDezenas) {
                                    menorValorSeisDezenas = valor;
                                }
                            }

                            break;
                        case 11: // ganhadores 5 acertos
                            valor = Integer.parseInt(((XSSFCell) cell).getRawValue());
                            qtdeGanhadoresCincoDezenasTodosConcursos += valor;

                            break;
                        case 12: // rateio 5 acertos
                            valor = Integer.parseInt(((XSSFCell) cell).getRawValue());

                            if (row.getRowNum() == 1) {
                                menorValorCincoDezenas = valor;
                            } else {
                                if (valor < menorValorCincoDezenas) {
                                    menorValorCincoDezenas = valor;
                                }
                            }

                            break;
                        case 13: // ganhadores 4 acertos
                            valor = Integer.parseInt(((XSSFCell) cell).getRawValue());
                            qtdeGanhadoresQuatroDezenasTodosConcursos += valor;

                            break;
                        case 14: // rateio 4 acertos
                            valor = Integer.parseInt(((XSSFCell) cell).getRawValue());

                            if (row.getRowNum() == 1) {
                                menorValorQuatroDezenas = valor;
                            } else {
                                if (valor < menorValorQuatroDezenas) {
                                    menorValorQuatroDezenas = valor;
                                }
                            }

                            break;
                        case 15: // acumulado 6 acertos
                            break;
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

        } catch (IOException ignored) {
        }
    }

}