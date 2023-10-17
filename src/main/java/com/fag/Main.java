package com.fag;

import com.fag.domain.entities.MegaSena;
import com.fag.domain.entities.MegaSenaStatistics;
import com.fag.domain.services.MegaSenaStatisticsService;
import com.fag.domain.services.WorkbookService;

import java.util.LinkedList;

public class Main {

    public static void main(String[] args) {
        String path = "./src/main/java/com/fag/data/Mega-Sena.xlsx";
        LinkedList<MegaSena> concursos = WorkbookService.instance().resolveWorkbookToMegaSenaList(path, 0);

        if (concursos == null || concursos.isEmpty()) {
            throw new RuntimeException("Ocorreu um erro durante o mapeamento!");
        }

        MegaSenaStatistics statistics = MegaSenaStatisticsService.instance().analyze(concursos);
        System.out.println(statistics);
    }

}