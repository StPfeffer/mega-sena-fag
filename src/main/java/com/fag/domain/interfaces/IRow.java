package com.fag.domain.interfaces;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.Iterator;

public interface IRow<ROW extends Row> {

    Iterator<Row> getRowIteratorFromSheet(Sheet sheet);

    Iterator<Cell> rowToCellIterator(ROW row);

}
