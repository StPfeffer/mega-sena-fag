package com.fag;

import com.fag.dto.MegaSenaDTO;
import com.fag.service.MegaSenaServiceImpl;

public class Main {

    public static void main(String[] args) {
        String path = "./src/main/java/com/fag/data/Mega-Sena.xlsx";

        MegaSenaDTO dto = MegaSenaServiceImpl.instance().analyzer(path);

        MegaSenaServiceImpl.printResults(dto);
    }

}