package com.fag.domain.interfaces;

import com.fag.domain.entities.MegaSena;
import com.fag.domain.entities.MegaSenaStatistics;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public interface IMegaSenaStatistics extends IStatistics<MegaSenaStatistics, MegaSena> {

    Map<Integer, Long> countNumberOccurrences(List<MegaSena> megaSenaList);

    int countNoWinners(List<MegaSena> megaSenaList);

    BigDecimal findMinPrize(List<MegaSena> megaSenaList, int hits);

    BigDecimal findMaxPrize(List<MegaSena> megaSenaList, int hits);

    BigDecimal findPrize(List<MegaSena> megaSenaList, int hits, Function<MegaSena, BigDecimal> prizeFunc);

    long countWinners(List<MegaSena> megaSenaList, int hits);

}
