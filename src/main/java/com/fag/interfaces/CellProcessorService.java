package com.fag.interfaces;

import com.fag.dto.MegaSenaDTO;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

@FunctionalInterface
public interface CellProcessorService {

    void process(Cell cell, Row row, MegaSenaDTO megaSenaDTO);

}
