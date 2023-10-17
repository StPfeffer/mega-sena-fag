package com.fag.domain.entities;

import java.math.BigDecimal;
import java.time.LocalDate;

public class MegaSena {

    private long concurso;
    private LocalDate dataConcurso;
    private int bola1;
    private int bola2;
    private int bola3;
    private int bola4;
    private int bola5;
    private int bola6;
    private int ganhadoresSeisAcertos;
    private String uf;
    private BigDecimal rateioSeisAcertos;
    private int ganhadoresCincoAcertos;
    private BigDecimal rateioCincoAcertos;
    private int ganhadoresQuatroAcertos;
    private BigDecimal rateioQuatroAcertos;;
    private BigDecimal acumuladoSeisAcertos;
    private BigDecimal arrecadacaoTotal;
    private BigDecimal estimativaPremio;
    private BigDecimal acumuladoEspecialMegaVirada;
    private String observacao;

    public long getConcurso() {
        return concurso;
    }

    public void setConcurso(long concurso) {
        this.concurso = concurso;
    }

    public LocalDate getDataConcurso() {
        return dataConcurso;
    }

    public void setDataConcurso(LocalDate dataConcurso) {
        this.dataConcurso = dataConcurso;
    }

    public int getBola1() {
        return bola1;
    }

    public void setBola1(int bola1) {
        this.bola1 = bola1;
    }

    public int getBola2() {
        return bola2;
    }

    public void setBola2(int bola2) {
        this.bola2 = bola2;
    }

    public int getBola3() {
        return bola3;
    }

    public void setBola3(int bola3) {
        this.bola3 = bola3;
    }

    public int getBola4() {
        return bola4;
    }

    public void setBola4(int bola4) {
        this.bola4 = bola4;
    }

    public int getBola5() {
        return bola5;
    }

    public void setBola5(int bola5) {
        this.bola5 = bola5;
    }

    public int getBola6() {
        return bola6;
    }

    public void setBola6(int bola6) {
        this.bola6 = bola6;
    }

    public int getGanhadoresSeisAcertos() {
        return ganhadoresSeisAcertos;
    }

    public void setGanhadoresSeisAcertos(int ganhadoresSeisAcertos) {
        this.ganhadoresSeisAcertos = ganhadoresSeisAcertos;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public BigDecimal getRateioSeisAcertos() {
        return rateioSeisAcertos;
    }

    public void setRateioSeisAcertos(BigDecimal rateioSeisAcertos) {
        this.rateioSeisAcertos = rateioSeisAcertos;
    }

    public int getGanhadoresCincoAcertos() {
        return ganhadoresCincoAcertos;
    }

    public void setGanhadoresCincoAcertos(int ganhadoresCincoAcertos) {
        this.ganhadoresCincoAcertos = ganhadoresCincoAcertos;
    }

    public BigDecimal getRateioCincoAcertos() {
        return rateioCincoAcertos;
    }

    public void setRateioCincoAcertos(BigDecimal rateioCincoAcertos) {
        this.rateioCincoAcertos = rateioCincoAcertos;
    }

    public int getGanhadoresQuatroAcertos() {
        return ganhadoresQuatroAcertos;
    }

    public void setGanhadoresQuatroAcertos(int ganhadoresQuatroAcertos) {
        this.ganhadoresQuatroAcertos = ganhadoresQuatroAcertos;
    }

    public BigDecimal getRateioQuatroAcertos() {
        return rateioQuatroAcertos;
    }

    public void setRateioQuatroAcertos(BigDecimal rateioQuatroAcertos) {
        this.rateioQuatroAcertos = rateioQuatroAcertos;
    }

    public BigDecimal getAcumuladoSeisAcertos() {
        return acumuladoSeisAcertos;
    }

    public void setAcumuladoSeisAcertos(BigDecimal acumuladoSeisAcertos) {
        this.acumuladoSeisAcertos = acumuladoSeisAcertos;
    }

    public BigDecimal getArrecadacaoTotal() {
        return arrecadacaoTotal;
    }

    public void setArrecadacaoTotal(BigDecimal arrecadacaoTotal) {
        this.arrecadacaoTotal = arrecadacaoTotal;
    }

    public BigDecimal getEstimativaPremio() {
        return estimativaPremio;
    }

    public void setEstimativaPremio(BigDecimal estimativaPremio) {
        this.estimativaPremio = estimativaPremio;
    }

    public BigDecimal getAcumuladoEspecialMegaVirada() {
        return acumuladoEspecialMegaVirada;
    }

    public void setAcumuladoEspecialMegaVirada(BigDecimal acumuladoEspecialMegaVirada) {
        this.acumuladoEspecialMegaVirada = acumuladoEspecialMegaVirada;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

}
