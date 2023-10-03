package com.fag.service;

import com.fag.dto.MegaSenaDTO;
import com.fag.interfaces.IMegaSenaService;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;

public class MegaSenaServiceImpl implements IMegaSenaService {

    public static MegaSenaServiceImpl instance() {
        return new MegaSenaServiceImpl();
    }

    @Override
    public void analyzer(String path) {
        File file = new File(path);

        try (FileInputStream fis = new FileInputStream(file)) {
            WorkbookServiceImpl workbookService = new WorkbookServiceImpl();
            Map<String, Integer> columnIndexes = workbookService.initializeColumnIndexes(fis);
            MegaSenaDTO dto = workbookService.processExcelFile(fis, columnIndexes, 0);

            printResults(dto);
        } catch (IOException ignored) {
        }
    }

    private static void printResults(MegaSenaDTO megaSenaDTO) {
        System.out.println("Quantidade de concursos sem seis dezenas: " + megaSenaDTO.getConcursosSemSeisDezenas());
        System.out.println("Menor valor quatro dezenas: " + megaSenaDTO.getMenorValorQuatroDezenas());
        System.out.println("Menor valor cinco dezenas: " + megaSenaDTO.getMenorValorCincoDezenas());
        System.out.println("Menor valor seis dezenas: " + megaSenaDTO.getMenorValorSeisDezenas());
        System.out.println("Maior valor quatro dezenas: " + megaSenaDTO.getMaiorValorQuatroDezenas());
        System.out.println("Maior valor cinco dezenas: " + megaSenaDTO.getMaiorValorCincoDezenas());
        System.out.println("Maior valor seis dezenas: " + megaSenaDTO.getMaiorValorSeisDezenas());
        System.out.println("Quantidade de ganhadores de quatro dezenas em todos os concursos: " + megaSenaDTO.getGanhadoresQuatroDezenasTodosConcursos());
        System.out.println("Quantidade de ganhadores de cinco dezenas em todos os concursos: " + megaSenaDTO.getGanhadoresCincoDezenasTodosConcursos());
        System.out.println("Quantidade de ganhadores de seis dezenas em todos os concursos: " + megaSenaDTO.getGanhadoresSeisDezenasTodosConcursos());
    }

}
