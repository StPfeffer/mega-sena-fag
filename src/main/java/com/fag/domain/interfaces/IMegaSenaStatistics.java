package com.fag.domain.interfaces;

import com.fag.domain.entities.MegaSena;
import com.fag.domain.entities.MegaSenaStatistics;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface IMegaSenaStatistics extends IStatistics<MegaSenaStatistics, MegaSena> {

    Map<Integer, Long> countNumberOccurrences(List<MegaSena> megaSenaList);

    int countNoWinners(List<MegaSena> megaSenaList);

    BigDecimal findMinPrize(List<MegaSena> megaSenaList, int hits);

    BigDecimal findMaxPrize(List<MegaSena> megaSenaList, int hits);

    long countWinners(List<MegaSena> megaSenaList, int hits);

}
