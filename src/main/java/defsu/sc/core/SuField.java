package defsu.sc.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import defsu.sc.utils.ComboAdapter;
import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SuField implements Serializable {
    @Serial
    private static final long serialVersionUID = 1123L;

    @JsonProperty("dataName")
    public String dataName;

    @JsonProperty("title")
    public String title;

    @JsonProperty("maskRegex")
    public MaskRegex maskRegex;

    @JsonProperty("defaultValue")
    public Object defaultValue;

    @JsonProperty("required")
    public boolean required;

    @JsonProperty("searchable")
    public boolean searchable;

    @JsonProperty("sortable")
    public boolean sortable;

    @JsonProperty("width")
    public int width;

    @JsonProperty("height")
    public int height;

    @JsonProperty("maxLength")
    public int maxLength;

    @JsonProperty("displayType")
    public DisplayType displayType;

    @JsonProperty("fieldType")
    public FieldType fieldType;

    @JsonProperty("fieldAlign")
    public FieldAlign fieldAlign;

    // for searchable combobox, checkbox, radio
    @JsonProperty("databaseRecord")
    public String databaseRecord;

    @JsonProperty("service")
    public String service;

    @JsonProperty("searchParams")
    public String searchParams;

    @JsonProperty("orderParams")
    public String orderParams;
    // SORT PARAMS LIKE "field1:asc,field2:desc"

    @JsonProperty("searchField")
    public String searchField;
    // SEARCH PARAMS LIKE "field1::searchValue::=,field2::searchValue::LIKE,field3::searchValue::NOTLIKE,field6::searchValue::<>,field7::searchValue::STARTSWITH,field8::searchValue::ENDSWITH"

    @JsonProperty("displayField")
    public String displayField;

    @JsonProperty("comboAdapter")
    public ComboAdapter comboAdapter;

    public SuField(){
        setFieldAlign(FieldAlign.LEFT);
        setFieldType(FieldType.STRING);
        setDisplayType(DisplayType.INPUT);
        setDataName("");
        setTitle("");
        setDefaultValue(null);
        setSearchable(true);
        setSortable(true);
        setRequired(false);
        setWidth(0);
        setHeight(0);
        setMaskRegex(MaskRegex.NONE);
        setDatabaseRecord("");
        setService("");
        setSearchParams("");
        setOrderParams("");
        setSearchField(null);
        setDisplayField("");
        setComboAdapter(null);
        setMaxLength(255);
    }

    public enum DisplayType{
        INPUT("input"),
        HIDDEN("hidden"),
        SELECT("select"),
        CHECKBOX("checkbox"),
        RADIO("radio"),
        SELECT2("select2"),
        TEXTAREA("textarea");
        private String value;
        DisplayType(String value) {
            this.value = value;
        }
    }

    public enum FieldType{
        STRING("string"),
        INTEGER("number"),
        DECIMAL("double"),
        BOOLEAN("boolean"),
        TIME("time"),
        LONG("long"),
        DATE("date"),
        DATETIME("datetime");
        private String value;
        FieldType(String value) {
            this.value = value;
        }
    }

    public enum FieldAlign{
        LEFT("left"),
        CENTER("center"),
        RIGHT("right");
        private String value;
        FieldAlign(String value) {
            this.value = value;
        }
    }

    public enum MaskRegex {
        NONE(""),
        CURRENCY("^[0-9]+(,[0-9]{1,2})?( ?₺)?$"),
        PERCENTAGE("^[0-9]+(,[0-9]{1,2})?%$"),
        INTEGER("^[0-9]+$"),
        DECIMAL("^[0-9]+(,[0-9]{1,2})?$"),
        CHAR("^[a-zA-ZçÇğĞıİöÖşŞüÜ]+$"),
        EMAIL("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"),
        PHONE("^(\\+90|0)[ ]?([0-9]{3})[ ]?([0-9]{3})[ ]?([0-9]{2})[ ]?([0-9]{2})$"),
        ZIPCODE("^[0-9]{5}$"),
        TC_ID("^[1-9][0-9]{10}$"),
        TAX_ID("^[0-9]{10}$"),
        DATE_TR("^(0[1-9]|[12][0-9]|3[01])\\.(0[1-9]|1[0-2])\\.([0-9]{4})$"),
        DATE_ISO("^[0-9]{4}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$"),
        TIME("^(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$"),
        DATETIME_TR("^(0[1-9]|[12][0-9]|3[01])\\.(0[1-9]|1[0-2])\\.([0-9]{4}) (0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$"),
        DATETIME_ISO("^[0-9]{4}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])T(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]$"),
        URL("^(http://www.|https://www.|http://|https://)?[a-z0-9]+([\\-\\.]{1}[a-z0-9]+)*\\.[a-z]{2,5}(:[0-9]{1,5})?(\\/.*)?$"),
        IP("^(?:(?:25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[1-9]?[0-9])\\.){3}(?:25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[1-9]?[0-9])$"),
        PASSWORD("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$"),
        IBAN("^TR[0-9]{2}[0-9]{5}[0-9]{1}[0-9]{16}$"),
        CREDIT_CARD("^[0-9]{4}( ?[0-9]{4}){3}$"),
        LICENSE_PLATE("^(0[1-9]|[1-7][0-9]|8[01])[ ]?([A-Z]{1,3})[ ]?([0-9]{2,4})$"),
        DOMAIN("^([a-zA-Z0-9]([a-zA-Z0-9\\-]{0,61}[a-zA-Z0-9])?\\.)+[a-zA-Z]{2,}$"),
        PHONE_INTERNATIONAL("^\\+[0-9]{1,3}[ ]?[0-9]{3,14}$"),
        ALPHANUMERIC("^[a-zA-Z0-9çÇğĞıİöÖşŞüÜ]+$"),
        NAME("^[a-zA-ZçÇğĞıİöÖşŞüÜ]+([ -][a-zA-ZçÇğĞıİöÖşŞüÜ]+)*$"),
        HEX_COLOR("^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$"),
        ADDRESS("^[a-zA-Z0-9çÇğĞıİöÖşŞüÜ\\.,\\-:;()&'\"/\\s]+$");

        private String value;

        MaskRegex(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    public static class FieldList implements Serializable{
        private final List<SuField> fields;
        private final List<String> fieldNames;

        public FieldList() {
            this.fields = new ArrayList<>();
            this.fieldNames = new ArrayList<>();
        }

        public int getCount() {
            return this.fields.size();
        }

        public SuField get(int index) {
            return this.fields.get(index);
        }

        public SuField get(String name) {
            for (int k = 0; k < this.fieldNames.size(); ++k) {
                if (this.fieldNames.get(k).equals(name)) {
                    return this.fields.get(k);
                }
            }
            return null;
        }

        public void set(int index, SuField field) {
            this.fields.set(index, field);
            this.fieldNames.set(index, field.getDataName());
        }

        public void add(SuField field) {
            this.fields.add(field);
            this.fieldNames.add(field.getDataName());
        }

        public int getSize() {
            return this.fields.size();
        }

        public List<SuField> getFields() {
            return this.fields;
        }

        public List<String> getFieldNames() {
            return this.fieldNames;
        }
    }

    public String getDataName() {
        return dataName;
    }

    public String getTitle() {
        return title;
    }

    public MaskRegex getMaskRegex() {
        return maskRegex;
    }

    public Object getDefaultValue() {
        return defaultValue;
    }

    public boolean getRequired() {
        return required;
    }

    public boolean getSearchable() {
        return searchable;
    }

    public boolean getSortable() {
        return sortable;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public DisplayType getDisplayType() {
        return displayType;
    }

    public FieldType getFieldType() {
        return fieldType;
    }

    public FieldAlign getFieldAlign() {
        return fieldAlign;
    }

    public String getDatabaseRecord() {
        return databaseRecord;
    }

    public String getService() {
        return service;
    }

    public String getSearchParams() {
        return searchParams;
    }

    public String getOrderParams() {
        return orderParams;
    }

    public String getSearchField() {
        return searchField;
    }

    public String getDisplayField() {
        return displayField;
    }

    public ComboAdapter getComboAdapter() {
        return comboAdapter;
    }

    public void setDataName(String dataName) {
        this.dataName = dataName;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setMaskRegex(MaskRegex maskRegex) {
        this.maskRegex = maskRegex;
    }

    public void setDefaultValue(Object defaultValue) {
        this.defaultValue = defaultValue;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public void setSearchable(boolean searchable) {
        this.searchable = searchable;
    }

    public void setSortable(boolean sortable) {
        this.sortable = sortable;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setDisplayType(DisplayType displayType) {
        this.displayType = displayType;
    }

    public void setFieldType(FieldType fieldType) {
        this.fieldType = fieldType;
    }

    public void setFieldAlign(FieldAlign fieldAlign) {
        this.fieldAlign = fieldAlign;
    }

    public void setDatabaseRecord(String databaseRecord) {
        this.databaseRecord = databaseRecord;
    }

    public void setService(String service) {
        this.service = service;
    }

    public void setSearchParams(String searchParams) {
        this.searchParams = searchParams;
    }

    public void setOrderParams(String orderParams) {
        this.orderParams = orderParams;
    }

    public void setSearchField(String searchField) {
        this.searchField = searchField;
    }

    public void setDisplayField(String displayField) {
        this.displayField = displayField;
    }

    public void setComboAdapter(ComboAdapter comboAdapter) {
        this.comboAdapter = comboAdapter;
    }


    public int getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
    }
}
