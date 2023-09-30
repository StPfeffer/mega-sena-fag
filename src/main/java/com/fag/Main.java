package com.fag;

import com.fag.domain.MegaSena;
import com.fag.mapper.MegaSenaMapper;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        String path = "./src/main/java/com/fag/data/Mega-Sena.xlsx";
        File file = new File(path);

        System.out.println(file.getPath());

        BufferedInputStream buf = new BufferedInputStream(new FileInputStream(file));
        List<MegaSena> megaSenaList = new ArrayList<>(MegaSenaMapper.dataToEntityList(buf));

        if (megaSenaList.isEmpty()) {
            System.out.println("null");
        }
    }

}