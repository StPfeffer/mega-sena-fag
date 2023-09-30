package com.fag.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record MegaSenaDTO(long concurso, LocalDate dataSorteio, int bola1, int bola2, int bola3, int bola4, int bola5, int bola6, int ganhadoresSeisAcertos, String uf, BigDecimal rateioSeisAcertos, int ganhadoresCincoAcertos, BigDecimal rateioCincoAcertos, int ganhadoresQuatroAcertos, int rateioQuatroAcertos, BigDecimal acumuladoSeisAcertos, BigDecimal arrecadacaoTotal, BigDecimal estimativaPremio, BigDecimal acumuladoSorteioEspecialMegaVirada, String observacao) {
}