package com.fag.domain.services;

import com.fag.domain.entities.MegaSena;
import com.fag.domain.entities.MegaSenaStatistics;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

class MegaSenaStatisticsServiceTest {

    private static List<MegaSena> megaSenaList;

    @BeforeAll
    static void populateMegaSenaList() {
        String path = "./src/main/java/com/fag/data/Mega-Sena.xlsx";
        megaSenaList = WorkbookService.instance().resolveWorkbookToMegaSenaList(path, 0);
    }

    static MegaSenaStatistics populateMegaSenaStatistics(List<MegaSena> megaSenaList, Integer index, Integer limit) {
        if (index == null && limit == null) {
            return MegaSenaStatisticsService.instance().analyze(megaSenaList);
        }

        if (limit != null) {
            return MegaSenaStatisticsService.instance().analyze(megaSenaList.subList(0, limit));
        }

        return MegaSenaStatisticsService.instance().analyze(megaSenaList.get(index));
    }

    @Test
    void assertTotalDraws() {
        Assertions.assertEquals(10, populateMegaSenaStatistics(megaSenaList, 0, 10).getTotalDraws());
        Assertions.assertEquals(1, populateMegaSenaStatistics(megaSenaList, 0, null).getTotalDraws());
        Assertions.assertEquals(2635, populateMegaSenaStatistics(megaSenaList, null, null).getTotalDraws());
        Assertions.assertEquals(548, populateMegaSenaStatistics(megaSenaList, null, 548).getTotalDraws());
    }

    @Test
    void assertNoWinnersCount() {
        Assertions.assertEquals(1, populateMegaSenaStatistics(megaSenaList, 0, 1).getNoWinnersCount());
        Assertions.assertEquals(0, populateMegaSenaStatistics(megaSenaList, 1, 0).getNoWinnersCount());
        Assertions.assertEquals(2039, populateMegaSenaStatistics(megaSenaList, null, null).getNoWinnersCount());
    }

    @Test
    void assertMinPrize4() {
        Assertions.assertEquals(new BigDecimal("330.21"), populateMegaSenaStatistics(megaSenaList, 0, null).getMinPrize4());
        Assertions.assertEquals(new BigDecimal("178.76"), populateMegaSenaStatistics(megaSenaList, 458, null).getMinPrize4());
        Assertions.assertEquals(new BigDecimal("69.55"), populateMegaSenaStatistics(megaSenaList, 458, 1638).getMinPrize4());
        Assertions.assertEquals(new BigDecimal("69.55"), populateMegaSenaStatistics(megaSenaList, null, null).getMinPrize4());
    }

    @Test
    void assertMinPrize5() {
        Assertions.assertEquals(new BigDecimal("39158.92"), populateMegaSenaStatistics(megaSenaList, 0, null).getMinPrize5());
        Assertions.assertEquals(new BigDecimal("12146.48"), populateMegaSenaStatistics(megaSenaList, 458, null).getMinPrize5());
        Assertions.assertEquals(new BigDecimal("122.53"), populateMegaSenaStatistics(megaSenaList, 458, 1638).getMinPrize5());
        Assertions.assertEquals(new BigDecimal("122.53"), populateMegaSenaStatistics(megaSenaList, null, null).getMinPrize5());
    }

    @Test
    void assertMinPrize6() {
        Assertions.assertEquals(BigDecimal.ZERO, populateMegaSenaStatistics(megaSenaList, 0, null).getMinPrize6());
        Assertions.assertEquals(BigDecimal.ZERO, populateMegaSenaStatistics(megaSenaList, 458, null).getMinPrize6());
        Assertions.assertEquals(new BigDecimal("348732.75"), populateMegaSenaStatistics(megaSenaList, 458, 1638).getMinPrize6());
        Assertions.assertEquals(new BigDecimal("348732.75"), populateMegaSenaStatistics(megaSenaList, null, null).getMinPrize6());
    }

    @Test
    void assertMaxPrize4() {
        Assertions.assertEquals(new BigDecimal("330.21"), populateMegaSenaStatistics(megaSenaList, 0, null).getMaxPrize4());
        Assertions.assertEquals(new BigDecimal("178.76"), populateMegaSenaStatistics(megaSenaList, 458, null).getMaxPrize4());
        Assertions.assertEquals(new BigDecimal("69.55"), populateMegaSenaStatistics(megaSenaList, 458, 1638).getMaxPrize4());
        Assertions.assertEquals(new BigDecimal("69.55"), populateMegaSenaStatistics(megaSenaList, null, null).getMaxPrize4());
    }

    @Test
    void assertMaxPrize5() {
        Assertions.assertEquals(new BigDecimal("39158.92"), populateMegaSenaStatistics(megaSenaList, 0, null).getMaxPrize5());
        Assertions.assertEquals(new BigDecimal("12146.48"), populateMegaSenaStatistics(megaSenaList, 458, null).getMaxPrize5());
        Assertions.assertEquals(new BigDecimal("122.53"), populateMegaSenaStatistics(megaSenaList, 458, 1638).getMaxPrize5());
        Assertions.assertEquals(new BigDecimal("122.53"), populateMegaSenaStatistics(megaSenaList, null, null).getMaxPrize5());
    }

    @Test
    void assertMaxPrize6() {
        Assertions.assertEquals(BigDecimal.ZERO, populateMegaSenaStatistics(megaSenaList, 0, null).getMaxPrize6());
        Assertions.assertEquals(BigDecimal.ZERO, populateMegaSenaStatistics(megaSenaList, 458, null).getMaxPrize6());
        Assertions.assertEquals(new BigDecimal("348732.75"), populateMegaSenaStatistics(megaSenaList, 458, 1638).getMaxPrize6());
        Assertions.assertEquals(new BigDecimal("348732.75"), populateMegaSenaStatistics(megaSenaList, null, null).getMaxPrize6());
    }

}