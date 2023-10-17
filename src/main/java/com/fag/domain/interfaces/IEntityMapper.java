package com.fag.domain.interfaces;

import org.apache.poi.ss.usermodel.Row;

import java.util.HashMap;

public interface IEntityMapper<ENTITY> extends IRowMapper {

    ENTITY rowToEntity(Row row);

    ENTITY attrsToEntity(HashMap<String, Object> attrs);

}