package com.fag.service;

import java.io.FileInputStream;

public class MegaSenaService {

    public static void analyzeData(FileInputStream fis) {
        WorkbookService.iterateThroughWorkbook(fis, 0);
    }

}
