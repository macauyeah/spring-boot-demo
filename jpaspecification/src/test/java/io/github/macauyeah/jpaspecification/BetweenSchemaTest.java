package io.github.macauyeah.jpaspecification;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class BetweenSchemaTest {
    @Test
    void testGenericAssign() {
        BetweenSchema<? extends Comparable<?>> betweenSchema;
        BetweenSchema<Double> betweenDouble = new BetweenSchema<Double>();
        betweenDouble.setLowerBound(1.0);
        betweenDouble.setUpperBound(2.0);
        BetweenSchema<Date> betweenDate = new BetweenSchema<Date>();
        betweenDate.setLowerBound(new Date());
        betweenDate.setUpperBound(new Date());

        betweenSchema = betweenDate;
        assertTrue(betweenSchema.getLowerBound() instanceof Date);
        betweenSchema = betweenDouble;
        assertTrue(betweenSchema.getLowerBound() instanceof Double);
        assertEquals(1.0, betweenSchema.getLowerBound());
        assertEquals(2.0, betweenSchema.getUpperBound());

        Map<String, BetweenSchema<? extends Comparable<?>>> betweenMap = new HashMap<>();
        betweenMap.put("date", betweenDate);
        betweenMap.put("double", betweenDouble);

        assertTrue(betweenMap.get("date").getLowerBound() instanceof Date);
        assertTrue(betweenMap.get("double").getLowerBound() instanceof Double);
        assertEquals(1.0, betweenMap.get("double").getLowerBound());
        assertEquals(2.0, betweenMap.get("double").getUpperBound());
    }
}
