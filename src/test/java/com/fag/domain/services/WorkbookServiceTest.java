package com.fag.domain.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class WorkbookServiceTest {

    private final String wrongPath = "./wrong/path/data.xlsx";
    private final String correctPath = "./src/main/java/com/fag/data/Mega-Sena.xlsx";

    @Test
    void resolveWorkbook() {
        Assertions.assertNull(WorkbookService.instance().resolveWorkbook(wrongPath));
        Assertions.assertNotNull(WorkbookService.instance().resolveWorkbook(correctPath));
    }

    @Test
    void resolveWorkbookToMegaSenaList() {
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> WorkbookService.instance().resolveWorkbookToMegaSenaList(correctPath, 0).get(2635));
        Assertions.assertNotNull(WorkbookService.instance().resolveWorkbookToMegaSenaList(correctPath, 0).get(2634));
        Assertions.assertNull(WorkbookService.instance().resolveWorkbookToMegaSenaList(correctPath, 0).get(2634).getRateioSeisAcertos());
    }

}