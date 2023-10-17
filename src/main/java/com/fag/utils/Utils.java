package com.fag.utils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Utils {

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
            if (resolvedValue.isEmpty()) {
                value = null;
            } else {
                value = new BigDecimal(resolvedValue);

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
