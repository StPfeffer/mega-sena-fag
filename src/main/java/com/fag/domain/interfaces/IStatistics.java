package com.fag.domain.interfaces;

import java.util.List;

public interface IStatistics<STATISTICS, ENTITY> {

    STATISTICS analyze(ENTITY entity);

    STATISTICS analyze(List<ENTITY> entityList);

}
