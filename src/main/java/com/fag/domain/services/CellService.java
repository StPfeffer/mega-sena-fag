package com.fag.domain.services;

import com.fag.domain.interfaces.ICell;
import org.apache.poi.xssf.usermodel.XSSFCell;

public class CellService implements ICell<XSSFCell> {

    public static CellService instance() {
        return new CellService();
    }

    @Override
    public Object resolveCellValueByType(XSSFCell cell) {
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

}
