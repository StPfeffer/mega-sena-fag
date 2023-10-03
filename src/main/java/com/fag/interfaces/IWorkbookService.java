package com.fag.interfaces;

import com.fag.dto.MegaSenaDTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;

public interface IWorkbookService {

    MegaSenaDTO processExcelFile(FileInputStream fis, Map<String, Integer> columnIndexes, int sheetIndex) throws IOException;

    Map<String, Integer> initializeColumnIndexes(FileInputStream fis) throws IOException;

}
