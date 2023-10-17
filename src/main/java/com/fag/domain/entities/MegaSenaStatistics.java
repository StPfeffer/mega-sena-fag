package com.fag.domain.entities;

import java.math.BigDecimal;
import java.util.Map;

public class MegaSenaStatistics {

    private int totalDraws;
    private Map<Integer, Long> numberOccurrences;
    private int noWinnersCount;
    private BigDecimal minPrize4;
    private BigDecimal minPrize5;
    private BigDecimal minPrize6;
    private BigDecimal maxPrize4;
    private BigDecimal maxPrize5;
    private BigDecimal maxPrize6;
    private long winnersCount4;
    private long winnersCount5;
    private long winnersCount6;

    public static class Builder {
        private final MegaSenaStatistics statistics;

        public Builder() {
            statistics = new MegaSenaStatistics();
        }

        public Builder withTotalDraws(int totalDraws) {
            statistics.setTotalDraws(totalDraws);
            return this;
        }

        public Builder withNumberOccurrences(Map<Integer, Long> numberOccurrences) {
            statistics.setNumberOccurrences(numberOccurrences);
            return this;
        }

        public Builder withNoWinnersCount(int noWinnersCount) {
            statistics.noWinnersCount = noWinnersCount;
            return this;
        }

        public Builder withMinPrize4(BigDecimal minPrize4) {
            statistics.setMinPrize4(minPrize4);
            return this;
        }

        public Builder withMinPrize5(BigDecimal minPrize5) {
            statistics.setMinPrize5(minPrize5);
            return this;
        }

        public Builder withMinPrize6(BigDecimal minPrize6) {
            statistics.setMinPrize6(minPrize6);
            return this;
        }

        public Builder withMinPrizes(BigDecimal minPrize4, BigDecimal minPrize5, BigDecimal minPrize6) {
            statistics.setMinPrize4(minPrize4);
            statistics.setMinPrize5(minPrize5);
            statistics.setMinPrize6(minPrize6);
            return this;
        }

        public Builder withMaxPrize4(BigDecimal maxPrize4) {
            statistics.setMaxPrize4(maxPrize4);
            return this;
        }

        public Builder withMaxPrize5(BigDecimal maxPrize5) {
            statistics.setMaxPrize5(maxPrize5);
            return this;
        }

        public Builder withMaxPrize6(BigDecimal maxPrize6) {
            statistics.setMaxPrize6(maxPrize6);
            return this;
        }

        public Builder withMaxPrizes(BigDecimal maxPrize4, BigDecimal maxPrize5, BigDecimal maxPrize6) {
            statistics.setMaxPrize4(maxPrize4);
            statistics.setMaxPrize5(maxPrize5);
            statistics.setMaxPrize6(maxPrize6);
            return this;
        }

        public Builder withWinnersCount4(long winnersCount4) {
            statistics.setWinnersCount4(winnersCount4);
            return this;
        }

        public Builder withWinnersCount5(long winnersCount5) {
            statistics.setWinnersCount5(winnersCount5);
            return this;
        }

        public Builder withWinnersCount6(long winnersCount6) {
            statistics.setWinnersCount6(winnersCount6);
            return this;
        }

        public Builder withWinnersCount(long winnersCount4, long winnersCount5, long winnersCount6) {
            statistics.setWinnersCount4(winnersCount4);
            statistics.setWinnersCount5(winnersCount5);
            statistics.setWinnersCount6(winnersCount6);
            return this;
        }

        public MegaSenaStatistics build() {
            return statistics;
        }
    }

    public int getTotalDraws() {
        return totalDraws;
    }

    public void setTotalDraws(int totalDraws) {
        this.totalDraws = totalDraws;
    }

    public Map<Integer, Long> getNumberOccurrences() {
        return numberOccurrences;
    }

    public void setNumberOccurrences(Map<Integer, Long> numberOccurrences) {
        this.numberOccurrences = numberOccurrences;
    }

    public int getNoWinnersCount() {
        return noWinnersCount;
    }

    public void setNoWinnersCount(int noWinnersCount) {
        this.noWinnersCount = noWinnersCount;
    }

    public BigDecimal getMinPrize4() {
        return minPrize4;
    }

    public void setMinPrize4(BigDecimal minPrize4) {
        this.minPrize4 = minPrize4;
    }

    public BigDecimal getMinPrize5() {
        return minPrize5;
    }

    public void setMinPrize5(BigDecimal minPrize5) {
        this.minPrize5 = minPrize5;
    }

    public BigDecimal getMinPrize6() {
        return minPrize6;
    }

    public void setMinPrize6(BigDecimal minPrize6) {
        this.minPrize6 = minPrize6;
    }

    public BigDecimal getMaxPrize4() {
        return maxPrize4;
    }

    public void setMaxPrize4(BigDecimal maxPrize4) {
        this.maxPrize4 = maxPrize4;
    }

    public BigDecimal getMaxPrize5() {
        return maxPrize5;
    }

    public void setMaxPrize5(BigDecimal maxPrize5) {
        this.maxPrize5 = maxPrize5;
    }

    public BigDecimal getMaxPrize6() {
        return maxPrize6;
    }

    public void setMaxPrize6(BigDecimal maxPrize6) {
        this.maxPrize6 = maxPrize6;
    }

    public long getWinnersCount4() {
        return winnersCount4;
    }

    public void setWinnersCount4(long winnersCount4) {
        this.winnersCount4 = winnersCount4;
    }

    public long getWinnersCount5() {
        return winnersCount5;
    }

    public void setWinnersCount5(long winnersCount5) {
        this.winnersCount5 = winnersCount5;
    }

    public long getWinnersCount6() {
        return winnersCount6;
    }

    public void setWinnersCount6(long winnersCount6) {
        this.winnersCount6 = winnersCount6;
    }

    @Override
    public String toString() {
        return  "Total de Sorteios: " + totalDraws + "\n"
                + "Sorteios Sem Vencedores para 6 Acertos: " + noWinnersCount + "\n"
                + "Ocorrências dos Números: " + numberOccurrences + "\n"
                + "Prêmio Mínimo para 4 Acertos: " + minPrize4 + "\n"
                + "Prêmio Mínimo para 5 Acertos: " + minPrize5 + "\n"
                + "Prêmio Mínimo para 6 Acertos: " + minPrize6 + "\n"
                + "Prêmio Máximo para 4 Acertos: " + maxPrize4 + "\n"
                + "Prêmio Máximo para 5 Acertos: " + maxPrize5 + "\n"
                + "Prêmio Máximo para 6 Acertos: " + maxPrize6 + "\n"
                + "Contagem de Vencedores (4 Acertos): " + winnersCount4 + "\n"
                + "Contagem de Vencedores (5 Acertos): " + winnersCount5 + "\n"
                + "Contagem de Vencedores (6 Acertos): " + winnersCount6 + "\n";
    }
}
