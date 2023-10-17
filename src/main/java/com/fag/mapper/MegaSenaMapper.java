package com.fag.mapper;

import com.fag.domain.MegaSena;
import com.fag.interfaces.IEntityMapper;
import com.fag.utils.Utils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MegaSenaMapper implements IEntityMapper<MegaSena> {

    public static MegaSenaMapper instance() {
        return new MegaSenaMapper();
    }

    @Override
    public MegaSena rowToEntity(Row row) {
        return new MegaSenaMapper().attrsToEntity(rowToAttrs(row));
    }

    @Override
    public HashMap<String, Object> rowToAttrs(Row row) {
        HashMap<String, Object> attrs = new HashMap<>();

        Iterator<Cell> cells = row.cellIterator();

        while (cells.hasNext()) {
            Cell cell = cells.next();
            int index = cell.getColumnIndex();

            Object value = Utils.resolveCellValueByType(cell);

            attrs.put(String.valueOf(index), value);
        }

        attrs = resolveAttrsKeys(attrs);

        return attrs;
    }

    @Override
    public MegaSena attrsToEntity(HashMap<String, Object> attrs) {
        MegaSena newMegaSena = new MegaSena();

        for (Map.Entry<String, Object> entry : attrs.entrySet()) {
            String fieldName = entry.getKey();
            Object value = entry.getValue();

            Field field;

            try {
                field = MegaSena.class.getDeclaredField(fieldName);
            } catch (NoSuchFieldException e) {
                throw new RuntimeException(e);
            }

            field.setAccessible(true);

            try {
                field.set(newMegaSena, Utils.castValue(field.getType(), value));
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }

        return newMegaSena;
    }

    public HashMap<String, Object> resolveAttrsKeys(HashMap<String, Object> attrs) {
        HashMap<String, Object> newAttrs = new HashMap<>();

        for (Map.Entry<String, Object> entry : attrs.entrySet()) {
            String key = entry.getKey();

            switch (key) {
                case "0" -> newAttrs.put("concurso", entry.getValue());
                case "1" -> newAttrs.put("dataConcurso", entry.getValue());
                case "2" -> newAttrs.put("bola1", entry.getValue());
                case "3" -> newAttrs.put("bola2", entry.getValue());
                case "4" -> newAttrs.put("bola3", entry.getValue());
                case "5" -> newAttrs.put("bola4", entry.getValue());
                case "6" -> newAttrs.put("bola5", entry.getValue());
                case "7" -> newAttrs.put("bola6", entry.getValue());
                case "8" -> newAttrs.put("ganhadoresSeisAcertos", entry.getValue());
                case "9" -> newAttrs.put("uf", entry.getValue());
                case "10" -> newAttrs.put("rateioSeisAcertos", entry.getValue());
                case "11" -> newAttrs.put("ganhadoresCincoAcertos", entry.getValue());
                case "12" -> newAttrs.put("rateioCincoAcertos", entry.getValue());
                case "13" -> newAttrs.put("ganhadoresQuatroAcertos", entry.getValue());
                case "14" -> newAttrs.put("rateioQuatroAcertos", entry.getValue());
                case "15" -> newAttrs.put("acumuladoSeisAcertos", entry.getValue());
                case "16" -> newAttrs.put("arrecadacaoTotal", entry.getValue());
                case "17" -> newAttrs.put("estimativaPremio", entry.getValue());
                case "18" -> newAttrs.put("acumuladoEspecialMegaVirada", entry.getValue());
                case "19" -> newAttrs.put("observacao", entry.getValue());
                case "20" -> newAttrs.put("teste", entry.getValue());
                default -> throw new UnsupportedOperationException("Não existe mapeamento para essa chave nos attrs");
            }
        }

        return newAttrs;
    }

}
