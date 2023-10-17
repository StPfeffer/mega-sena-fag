package com.fag.domain.mappers;

import com.fag.domain.entities.MegaSena;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.HashMap;

class MegaSenaMapperTest {

    @Test
    void attrsToEntity() {
        HashMap<String, Object> attrs = new HashMap<>();
        attrs.put("concurso", "1");
        attrs.put("dataConcurso", "11/03/1996");
        attrs.put("bola1", "4");
        attrs.put("bola2", "5");
        attrs.put("bola3", "30");
        attrs.put("bola4", "33");
        attrs.put("bola5", "41");
        attrs.put("bola6", "52");
        attrs.put("ganhadoresSeisAcertos", "0");
        attrs.put("uf", "");
        attrs.put("rateioSeisAcertos", "R$0,00");
        attrs.put("ganhadoresCincoAcertos", "17");
        attrs.put("rateioCincoAcertos", "R$39.158,92");
        attrs.put("ganhadoresQuatroAcertos", "2016");
        attrs.put("rateioQuatroAcertos", "R$330,21");
        attrs.put("acumuladoSeisAcertos", "R$1.714.650,23");
        attrs.put("arrecadacaoTotal", "R$0,00");
        attrs.put("estimativaPremio", "R$0,00");
        attrs.put("acumuladoEspecialMegaVirada", "R$0,00");
        attrs.put("observacao", "");

        MegaSena megaSenaTeste = MegaSenaMapper.instance().attrsToEntity(attrs);

        Assertions.assertNotNull(megaSenaTeste);
        Assertions.assertEquals(BigDecimal.ZERO, megaSenaTeste.getRateioSeisAcertos());
        Assertions.assertNull(megaSenaTeste.getUf());
    }

    @Test
    void resolveAttrKeys() {
        HashMap<String, Object> attrs = new HashMap<>();
        attrs.put("0", "Concurso 123");
        attrs.put("1", "01/01/2022");
        attrs.put("18", "R$ 586.495,34");

        HashMap<String, Object> newAttrs = MegaSenaMapper.instance().resolveAttrsKeys(attrs);

        Assertions.assertEquals(attrs.get("0"), newAttrs.get("concurso"));
        Assertions.assertEquals(attrs.get("1"), newAttrs.get("dataConcurso"));
        Assertions.assertEquals(attrs.get("18"), newAttrs.get("acumuladoEspecialMegaVirada"));
    }

    @Test
    public void testResolveAttrsKeysWithUnsupportedKey() {
        HashMap<String, Object> attrs = new HashMap<>();
        attrs.put("21", "Unsupported Value");

        Assertions.assertThrows(UnsupportedOperationException.class, () -> MegaSenaMapper.instance().resolveAttrsKeys(attrs));
    }

}