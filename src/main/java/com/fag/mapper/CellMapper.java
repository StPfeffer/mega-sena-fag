package com.fag.mapper;

import com.fag.domain.MegaSena;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.TimeZone;

public class CellMapper {

    public static void cellToProperty(Iterator<Cell> cells, MegaSena megaSena) {
        while (cells.hasNext()) {
            Cell cell = cells.next();

            resolveCellColumn(((XSSFCell) cell).getRawValue(), cell.getColumnIndex(), megaSena);
        }
    }

    private static void resolveCellColumn(String value, Integer index, MegaSena megaSena) {
        switch (index + 1) {
            case 1 -> megaSena.setConcurso(Long.parseLong(value) + 1);
            case 2 -> {
                long longDate = 0L;

                try {
                    longDate = new SimpleDateFormat("dd/MM/yyyy").parse(value).getTime();
                } catch (ParseException ignored) {
                }

                LocalDate localDate = LocalDate.ofInstant(Instant.ofEpochMilli(longDate), TimeZone.getDefault().toZoneId());
                megaSena.setDataSorteio(localDate);
            }
            case 3 -> megaSena.setBola1(Integer.parseInt(value));
            case 4 -> megaSena.setBola2(Integer.parseInt(value));
            case 5 -> megaSena.setBola3(Integer.parseInt(value));
            case 6 -> megaSena.setBola4(Integer.parseInt(value));
            case 7 -> megaSena.setBola5(Integer.parseInt(value));
            case 8 -> megaSena.setBola6(Integer.parseInt(value));
            case 9 -> megaSena.setGanhadoresSeisAcertos(Integer.parseInt(value));
            case 10 -> megaSena.setUf(value);
            case 11 -> megaSena.setRateioSeisAcertos(new BigDecimal(value));
            case 12 -> megaSena.setGanhadoresCincoAcertos(Integer.parseInt(value));
            case 13 -> megaSena.setRateioCincoAcertos(new BigDecimal(value));
            case 14 -> megaSena.setGanhadoresQuatroAcertos(Integer.parseInt(value));
            case 15 -> megaSena.setRateioQuatroAcertos(new BigDecimal(value));
            case 16 -> megaSena.setAcumuladoSeisAcertos(new BigDecimal(value));
            case 17 -> megaSena.setArrecadacaoTotal(new BigDecimal(value));
            case 18 -> megaSena.setEstimativaPremio(new BigDecimal(value));
            case 19 -> megaSena.setAcumuladoSorteioEspecialMegaVirada(new BigDecimal(value));
            case 20 -> megaSena.setObservacao(value);
            default -> throw new UnsupportedOperationException("Não existe mapeamento para essa coluna");
        }
    }

}
