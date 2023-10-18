package com.fag.domain.services;

import com.fag.domain.entities.MegaSena;
import com.fag.domain.entities.MegaSenaStatistics;
import com.fag.domain.interfaces.IMegaSenaStatistics;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MegaSenaStatisticsService implements IMegaSenaStatistics {

    public static MegaSenaStatisticsService instance() {
        return new MegaSenaStatisticsService();
    }

    @Override
    public MegaSenaStatistics analyze(MegaSena megaSena) {
        return analyze(Collections.singletonList(megaSena));
    }

    @Override
    public MegaSenaStatistics analyze(List<MegaSena> megaSenaList) {
        int totalDraws = megaSenaList.size();
        Map<Integer, Long> numberOccurrences = countNumberOccurrences(megaSenaList);
        int noWinnersCount = countNoWinners(megaSenaList);

        BigDecimal minPrize4 = findMinPrize(megaSenaList, 4);
        BigDecimal minPrize5 = findMinPrize(megaSenaList, 5);
        BigDecimal minPrize6 = findMinPrize(megaSenaList, 6);

        BigDecimal maxPrize4 = findMaxPrize(megaSenaList, 4);
        BigDecimal maxPrize5 = findMaxPrize(megaSenaList, 5);
        BigDecimal maxPrize6 = findMaxPrize(megaSenaList, 6);

        long winnersCount4 = countWinners(megaSenaList, 4);
        long winnersCount5 = countWinners(megaSenaList, 5);
        long winnersCount6 = countWinners(megaSenaList, 6);

        return new MegaSenaStatistics.Builder()
                .withTotalDraws(totalDraws)
                .withNumberOccurrences(numberOccurrences)
                .withNoWinnersCount(noWinnersCount)
                .withMinPrizes(minPrize4, minPrize5, minPrize6)
                .withMaxPrizes(maxPrize4, maxPrize5, maxPrize6)
                .withWinnersCount(winnersCount4, winnersCount5, winnersCount6)
                .build();
    }

    @Override
    public Map<Integer, Long> countNumberOccurrences(List<MegaSena> megaSenaList) {
        return megaSenaList.stream()
                .flatMap(megaSena -> Stream.of(megaSena.getBola1(), megaSena.getBola2(), megaSena.getBola3(), megaSena.getBola4(), megaSena.getBola5(), megaSena.getBola6()))
                .collect(Collectors.groupingBy(number -> number, Collectors.counting()));
    }

    @Override
    public int countNoWinners(List<MegaSena> megaSenaList) {
        return (int) megaSenaList.stream()
                .filter(megaSena -> megaSena.getGanhadoresSeisAcertos() == 0)
                .count();
    }

    @Override
    public BigDecimal findMinPrize(List<MegaSena> megaSenaList, int hits) {
        return megaSenaList.stream()
                .map(ms -> switch (hits) {
                    case 4 -> ms.getRateioQuatroAcertos();
                    case 5 -> ms.getRateioCincoAcertos();
                    case 6 -> ms.getRateioSeisAcertos();
                    default -> throw new IllegalArgumentException("Invalid number of hits: " + hits);
                })
                .filter(prize -> Objects.nonNull(prize) && prize.compareTo(BigDecimal.ZERO) > 0)
                .min(BigDecimal::compareTo)
                .orElse(BigDecimal.ZERO);
    }

    @Override
    public BigDecimal findMaxPrize(List<MegaSena> megaSenaList, int hits) {
        return megaSenaList.stream()
                .map(ms -> switch (hits) {
                    case 4 -> ms.getRateioQuatroAcertos();
                    case 5 -> ms.getRateioCincoAcertos();
                    case 6 -> ms.getRateioSeisAcertos();
                    default -> throw new IllegalArgumentException("Invalid number of hits: " + hits);
                })
                .filter(prize -> Objects.nonNull(prize) && prize.compareTo(BigDecimal.ZERO) > 0)
                .max(BigDecimal::compareTo)
                .orElse(BigDecimal.ZERO);
    }

    @Override
    public long countWinners(List<MegaSena> megaSenaList, int hits) {
        return megaSenaList.stream()
                .mapToLong(ms -> switch (hits) {
                    case 4 -> ms.getGanhadoresQuatroAcertos();
                    case 5 -> ms.getGanhadoresCincoAcertos();
                    case 6 -> ms.getGanhadoresSeisAcertos();
                    default -> throw new IllegalArgumentException("Invalid number of hits: " + hits);
                })
                .sum();
    }

}
