package com.fag.service;

import org.apache.poi.ss.usermodel.Row;

import java.util.Iterator;

public class RowService {

    public static void iterateThroughRow(Iterator<Row> rows) {
        while (rows.hasNext()) {
            Row row = rows.next();
        }
    }

}
