package com.fag;

import com.fag.domain.MegaSena;
import com.fag.service.MegaSenaStatistics;
import com.fag.service.WorkbookService;

import java.util.LinkedList;

public class Main {

    public static void main(String[] args) {
        String path = "./src/main/java/com/fag/data/Mega-Sena.xlsx";
        LinkedList<MegaSena> concursos = WorkbookService.instance().resolveWorkbook(path, 0);

        if (concursos == null || concursos.isEmpty()) {
            throw new RuntimeException("Ocorreu um erro durante o mapeamento!");
        }

        MegaSenaStatistics.execute(concursos);
    }

}