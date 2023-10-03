package com.fag;

import com.fag.service.MegaSenaServiceImpl;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) {
        String path = "./src/main/java/com/fag/data/Mega-Sena.xlsx";

        MegaSenaServiceImpl.instance().analyzer(path);
    }

}