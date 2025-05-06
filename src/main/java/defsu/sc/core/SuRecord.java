package defsu.sc.core;

import java.io.Serializable;


public interface SuRecord extends Serializable {

    void initialize();
    void process();

    SuResponse save();
    SuResponse delete();
    boolean validate();
    boolean disableLog();
    boolean hasDuplicate();
    RecordProps getField();

    public static class RecordProps implements Serializable{
        public String title;
        public String idField;
        public SuField.FieldList fields;
    }
}
