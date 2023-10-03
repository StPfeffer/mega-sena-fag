package com.fag.interfaces;

import com.fag.domain.AbstractEntity;
import org.apache.poi.ss.usermodel.Row;

import java.util.HashMap;

public interface IEntityMapper<ENTITY extends AbstractEntity> extends IRowMapper {

    ENTITY rowToEntity(Row row);

    ENTITY attrsToEntity(HashMap<String, Object> attrs);

}