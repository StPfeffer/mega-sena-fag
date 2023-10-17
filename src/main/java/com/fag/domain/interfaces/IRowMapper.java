package com.fag.domain.interfaces;

import org.apache.poi.ss.usermodel.Row;

import java.util.HashMap;

public interface IRowMapper {

    default HashMap<String, Object> rowToAttrs(Row row) {
        return new HashMap<>();
    }

}
