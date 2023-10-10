package com.fag.service;

import com.fag.dto.MegaSenaDTO;
import org.junit.Assert;
import org.junit.Test;

public class MegaSenaServiceImplTest {

    @Test
    public void analyzerTeste() {
        MegaSenaDTO dto = MegaSenaServiceImpl.instance().analyzer("./src/main/java/com/fag/data/Mega-Sena.xlsx");

        Assert.assertEquals("Espera-se que a quantidade de concursos sem as seis dezenas sorteas seja 2038.", dto.getConcursosSemSeisDezenas(), 2038);
    }

}
