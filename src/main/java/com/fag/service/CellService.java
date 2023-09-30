package com.fag.service;

public class CellService {

    public static Class<?> resolveCellType(int type) {
        return switch (type) {
            case 0 -> Integer.class;
            case 4 -> Boolean.class;
            default -> String.class;
        };
    }

}
