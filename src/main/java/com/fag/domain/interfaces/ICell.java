package com.fag.domain.interfaces;

import org.apache.poi.ss.usermodel.Cell;

public interface ICell<CELL extends Cell> {

    Object resolveCellValueByType(CELL cell);

}
