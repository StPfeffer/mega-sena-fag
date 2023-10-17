package com.fag.utils;

import org.apache.poi.ss.usermodel.Cell;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Utils {

    public static Object resolveCellValueByType(Cell cell) {
        int cellType = cell.getCellType();

        return switch (cellType) {
            case 0 -> {
                double doubleVal = cell.getNumericCellValue();

                if (doubleVal == (int) doubleVal) {
                    yield Double.valueOf(doubleVal).intValue();
                }

                yield doubleVal;
            }
            case 1 -> cell.getStringCellValue();
            case 2 -> cell.getCellFormula();
            case 3 -> "";
            case 4 -> cell.getBooleanCellValue();
            case 5 -> cell.getErrorCellValue();
            default -> null;
        };

    }

    public static Object castValue(Class<?> clazz, Object value) {
        if (value instanceof String && ((String) value).isBlank()) {
            value = null;
        }

        if (value == null) {
            return null;
        }

        if (clazz.isInstance(value)) {
            return value;
        }

        String resolvedValue = resolveValue(value);

        if (clazz.equals(int.class) || clazz.equals(Integer.class)) {
            value = Integer.parseInt(resolvedValue);
        } else if (clazz.equals(double.class) || clazz.equals(Double.class)) {
            value = Double.parseDouble(resolvedValue);
        } else if (clazz.equals(long.class) || clazz.equals(Long.class)) {
            value = Long.parseLong(resolvedValue);
        } else if (clazz.equals(float.class) || clazz.equals(Float.class)) {
            value = Float.parseFloat(resolvedValue);
        } else if (clazz.equals(String.class)) {
            value = resolvedValue;
        } else if (clazz.equals(BigDecimal.class)) {
            String formattedValue = resolveValue(value);

            if (formattedValue.isEmpty()) {
                value = null;
            } else {
                value = new BigDecimal(formattedValue);

                if (value.equals(new BigDecimal("0.00"))) {
                    value = BigDecimal.ZERO;
                }
            }
        } else if (clazz.equals(LocalDate.class)) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            value = LocalDate.parse(value.toString(), formatter);
        } else {
            throw new IllegalArgumentException("Unsupported data type: " + clazz);
        }

        return value;
    }

    public static String resolveValue(Object value) {
        return value.toString().replaceAll("[^\\d.,]+", "").replaceAll("\\.", "").replaceAll(",", ".");
    }

}
