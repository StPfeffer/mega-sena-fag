package com.fag.dto;

import java.math.BigDecimal;

public class MegaSenaDTO {
    private int concursosSemSeisDezenas = 0;
    private BigDecimal menorValorQuatroDezenas = BigDecimal.ZERO;
    private BigDecimal menorValorCincoDezenas = BigDecimal.ZERO;
    private BigDecimal menorValorSeisDezenas = BigDecimal.ZERO;
    private BigDecimal maiorValorQuatroDezenas = BigDecimal.ZERO;
    private BigDecimal maiorValorCincoDezenas = BigDecimal.ZERO;
    private BigDecimal maiorValorSeisDezenas = BigDecimal.ZERO;
    private int ganhadoresQuatroDezenasTodosConcursos = 0;
    private int ganhadoresCincoDezenasTodosConcursos = 0;
    private int ganhadoresSeisDezenasTodosConcursos = 0;

    public void incrementConcursosSemSeisDezenas() {
        concursosSemSeisDezenas++;
    }

    public void incrementGanhadoresSeisDezenas(int valor) {
        ganhadoresSeisDezenasTodosConcursos += valor;
    }

    public void incrementGanhadoresCincoDezenas(int valor) {
        ganhadoresCincoDezenasTodosConcursos += valor;
    }

    public void incrementGanhadoresQuatroDezenas(int valor) {
        ganhadoresQuatroDezenasTodosConcursos += valor;
    }

    public void setMenorValorQuatroDezenas(BigDecimal value) {
        menorValorQuatroDezenas = value;
    }

    public void setMaiorValorQuatroDezenas(BigDecimal value) {
        maiorValorQuatroDezenas = value;
    }

    public void setMenorValorCincoDezenas(BigDecimal value) {
        menorValorCincoDezenas = value;
    }

    public void setMaiorValorCincoDezenas(BigDecimal value) {
        maiorValorCincoDezenas = value;
    }

    public void setMenorValorSeisDezenas(BigDecimal value) {
        menorValorSeisDezenas = value;
    }

    public void setMaiorValorSeisDezenas(BigDecimal value) {
        maiorValorSeisDezenas = value;
    }

    public int getConcursosSemSeisDezenas() {
        return concursosSemSeisDezenas;
    }

    public BigDecimal getMenorValorQuatroDezenas() {
        return menorValorQuatroDezenas;
    }

    public BigDecimal getMaiorValorQuatroDezenas() {
        return maiorValorQuatroDezenas;
    }

    public BigDecimal getMenorValorCincoDezenas() {
        return menorValorCincoDezenas;
    }

    public BigDecimal getMaiorValorCincoDezenas() {
        return maiorValorCincoDezenas;
    }

    public BigDecimal getMenorValorSeisDezenas() {
        return menorValorSeisDezenas;
    }

    public BigDecimal getMaiorValorSeisDezenas() {
        return maiorValorSeisDezenas;
    }

    public int getGanhadoresQuatroDezenasTodosConcursos() {
        return ganhadoresQuatroDezenasTodosConcursos;
    }

    public int getGanhadoresCincoDezenasTodosConcursos() {
        return ganhadoresCincoDezenasTodosConcursos;
    }

    public int getGanhadoresSeisDezenasTodosConcursos() {
        return ganhadoresSeisDezenasTodosConcursos;
    }

}