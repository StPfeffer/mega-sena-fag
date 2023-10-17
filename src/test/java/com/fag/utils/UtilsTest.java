package com.fag.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

class UtilsTest {

    @Test
    void castValue() {
        Assertions.assertTrue(Utils.castValue(Integer.class, "3847") instanceof Integer);
        Assertions.assertTrue(Utils.castValue(Double.class, "R$ 100,00") instanceof Double);
        Assertions.assertTrue(Utils.castValue(Long.class, "384734787344945") instanceof Long);
        Assertions.assertTrue(Utils.castValue(Float.class, "3847.456") instanceof Float);
        Assertions.assertTrue(Utils.castValue(String.class, "3847") instanceof String);
        Assertions.assertTrue(Utils.castValue(BigDecimal.class, "4378.456") instanceof BigDecimal);
        Assertions.assertTrue(Utils.castValue(LocalDate.class, "01/01/2001") instanceof LocalDate);

        Assertions.assertFalse(Utils.castValue(Integer.class, "3847") instanceof Long);
        Assertions.assertFalse(Utils.castValue(Double.class, "R$ 100,00") instanceof String);
        Assertions.assertFalse(Utils.castValue(Long.class, "384734787344945") instanceof Integer);
        Assertions.assertFalse(Utils.castValue(Float.class, "3847.456") instanceof Double);
        Assertions.assertFalse(Utils.castValue(String.class, "3847") instanceof BigDecimal);
        Assertions.assertFalse(Utils.castValue(BigDecimal.class, "4378.456") instanceof LocalDate);
        Assertions.assertFalse(Utils.castValue(LocalDate.class, "01/01/2001") instanceof Float);
    }

    @Test
    void resolveValue() {
        Assertions.assertEquals("220.00", Utils.resolveValue("R$220,00"));
        Assertions.assertEquals("220.0", Utils.resolveValue("R$220,0"));
        Assertions.assertEquals("1750.94", Utils.resolveValue("R$ 1750,94"));
        Assertions.assertEquals("468.4769", Utils.resolveValue("R$ 468,4769"));
        Assertions.assertEquals("3454234.97", Utils.resolveValue("R$ 3.454.234,97"));
    }

}