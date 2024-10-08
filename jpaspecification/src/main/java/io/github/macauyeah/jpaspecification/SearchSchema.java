package io.github.macauyeah.jpaspecification;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchSchema {
    private Map<String, String> stringValues = new HashMap<>();
    private Map<String, String> subStringValues = new HashMap<>();
    private Map<String, Date> dateValues = new HashMap<>();
    private Map<String, Date> dateGreaterThan = new HashMap<>();
    private Map<String, Date> dateLessThan = new HashMap<>();
    private Map<String, BetweenSchema<Date>> dateBetween = new HashMap<>();
    private Map<String, SearchSchema> joinValues = new HashMap<>();
    private Map<String, Integer> integerValues = new HashMap<>();
    private Map<String, Double> doubleValues = new HashMap<>();

    public void addStringValuesByReflection(List<String> names, Object instance)
            throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        for (String name : names) {
            Field field = instance.getClass().getDeclaredField(name);
            field.setAccessible(true);
            this.stringValues.put(name, (String) field.get(instance));
        }
    }

    public Map<String, String> getStringValues() {
        return stringValues;
    }

    public void setStringValues(Map<String, String> stringValues) {
        this.stringValues = stringValues;
    }

    public Map<String, Date> getDateValues() {
        return dateValues;
    }

    public void setDateValues(Map<String, Date> dateValues) {
        this.dateValues = dateValues;
    }

    public Map<String, SearchSchema> getJoinValues() {
        return joinValues;
    }

    public void setJoinValues(Map<String, SearchSchema> joinValues) {
        this.joinValues = joinValues;
    }

    public Map<String, Date> getDateGreaterThan() {
        return dateGreaterThan;
    }

    public void setDateGreaterThan(Map<String, Date> dateGreaterThan) {
        this.dateGreaterThan = dateGreaterThan;
    }

    public Map<String, Date> getDateLessThan() {
        return dateLessThan;
    }

    public void setDateLessThan(Map<String, Date> dateLessThan) {
        this.dateLessThan = dateLessThan;
    }

    public Map<String, String> getSubStringValues() {
        return subStringValues;
    }

    public void setSubStringValues(Map<String, String> subStringValues) {
        this.subStringValues = subStringValues;
    }

    public Map<String, BetweenSchema<Date>> getDateBetween() {
        return dateBetween;
    }

    public void setDateBetween(Map<String, BetweenSchema<Date>> dateBetween) {
        this.dateBetween = dateBetween;
    }

    public Map<String, Integer> getIntegerValues() {
        return integerValues;
    }

    public void setIntegerValues(Map<String, Integer> integerValues) {
        this.integerValues = integerValues;
    }

    public Map<String, Double> getDoubleValues() {
        return doubleValues;
    }

    public void setDoubleValues(Map<String, Double> doubleValues) {
        this.doubleValues = doubleValues;
    }

    
}
