package com.fag.service;

import com.fag.domain.MegaSena;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MegaSenaStatistics {

    public static void execute(List<MegaSena> megaSenaList) {
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

        System.out.println("Total de Sorteios: " + totalDraws);
        System.out.println("Ocorrências dos Números: " + numberOccurrences);
        System.out.println("Sorteios Sem Vencedores: " + noWinnersCount);
        System.out.println("Prêmio Mínimo para 4 Acertos: " + minPrize4);
        System.out.println("Prêmio Mínimo para 5 Acertos: " + minPrize5);
        System.out.println("Prêmio Mínimo para 6 Acertos: " + minPrize6);
        System.out.println("Prêmio Máximo para 4 Acertos: " + maxPrize4);
        System.out.println("Prêmio Máximo para 5 Acertos: " + maxPrize5);
        System.out.println("Prêmio Máximo para 6 Acertos: " + maxPrize6);
        System.out.println("Contagem de Vencedores (4 Acertos): " + winnersCount4);
        System.out.println("Contagem de Vencedores (5 Acertos): " + winnersCount5);
        System.out.println("Contagem de Vencedores (6 Acertos): " + winnersCount6);
    }

    private static Map<Integer, Long> countNumberOccurrences(List<MegaSena> megaSenaList) {
        return megaSenaList.stream()
                .flatMap(megaSena -> Stream.of(megaSena.getBola1(), megaSena.getBola2(), megaSena.getBola3(), megaSena.getBola4(), megaSena.getBola5(), megaSena.getBola6()))
                .collect(Collectors.groupingBy(number -> number, Collectors.counting()));
    }

    private static int countNoWinners(List<MegaSena> megaSenaList) {
        return (int) megaSenaList.stream()
                .filter(megaSena -> megaSena.getGanhadoresSeisAcertos() == 0)
                .count();
    }

    private static BigDecimal findMinPrize(List<MegaSena> megaSenaList, int hits) {
        return megaSenaList.stream()
                .filter(megaSena -> megaSena.getGanhadoresSeisAcertos() == hits)
                .map(MegaSena::getRateioSeisAcertos)
                .min(BigDecimal::compareTo)
                .orElse(BigDecimal.ZERO);
    }

    private static BigDecimal findMaxPrize(List<MegaSena> megaSenaList, int hits) {
        return megaSenaList.stream()
                .filter(megaSena -> megaSena.getGanhadoresSeisAcertos() == hits)
                .map(MegaSena::getRateioSeisAcertos)
                .max(BigDecimal::compareTo)
                .orElse(BigDecimal.ZERO);
    }

    private static long countWinners(List<MegaSena> megaSenaList, int hits) {
        return megaSenaList.stream()
                .filter(megaSena -> megaSena.getGanhadoresSeisAcertos() == hits)
                .count();
    }

}
