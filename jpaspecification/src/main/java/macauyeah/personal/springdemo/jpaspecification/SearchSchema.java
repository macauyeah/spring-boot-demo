package macauyeah.personal.springdemo.jpaspecification;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class SearchSchema<E> {
    private Map<String, String> stringValues = new HashMap<>();
    private Map<String, Date> dateValues = new HashMap<>();
    private Map<String, SearchSchema<?>> joinValues = new HashMap<>();

    private final Class<E> type;

    public SearchSchema(Class<E> type) {
         this.type = type;
    }
    public Class<E> getMyType() {
        return this.type;
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
    
}
